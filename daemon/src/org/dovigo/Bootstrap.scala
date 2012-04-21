/**
 * Copyright (c) 2012 Hannes Moser
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * ofthis software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.dovigo

import org.apache.commons.configuration.Configuration
import org.dovigo.process.Scheduler
import scala.actors.threadpool.BlockingQueue
import java.util.concurrent.ArrayBlockingQueue
import org.dovigo.cli.Command
import org.dovigo.log.Logging
import org.dovigo.dmes.parser.XmlParser

/**
 * Bootstrapping
 *
 * @author Hannes Moser
 * @version 0.1
 * @since 0.1
 */
object Bootstrap extends Logging {

	/**
	 * Initialize the application
	 */
	def init(conf: Configuration) = {
		val xml =
			<dmes id="UID-1">
				<!-- MP4 720p -->
				<message id="msg-1" extends="web-mp4-hq">
					<file input-file="assets/trailer_720p.mp4" output-file="output/trailer_720p.mp4"/>
				</message>
				<!-- MP4 480p -->
				<message id="msg-6" extends="web-mp4-hq">
					<file input-file="assets/trailer_720p.mp4" output-file="output/trailer_480p.mp4"/>
					<properties>
						<entry key="s">hd480</entry>
						<entry key="b">1500K</entry>
						<entry key="bt">2000K</entry>
					</properties>
				</message>
				<!-- MP4 360p -->
				<message id="msg-7" extends="web-mp4-hq">
					<file input-file="assets/trailer_720p.mp4" output-file="output/trailer_360p.mp4"/>
					<properties>
						<entry key="s">640x360</entry>
						<entry key="b">1M</entry>
						<entry key="bt">1500K</entry>
					</properties>
				</message>
				<!-- MP4 240p -->
				<message id="msg-8" extends="web-mp4-hq">
					<file input-file="assets/trailer_720p.mp4" output-file="output/trailer_240p.mp4"/>
					<properties>
						<entry key="s">426x240</entry>
						<entry key="b">750K</entry>
						<entry key="bt">1M</entry>
					</properties>
				</message>
				<!-- Make result streamable - 240p -->
				<message id="msg-2" pid="msg-8" extends="web-f4v">
					<file input-file="output/trailer_240p.mp4" output-path="output/"/>
					<properties>
						<entry key="bitrate">750</entry>
					</properties>
				</message>
				<!-- Make result streamable - 360p -->
				<message id="msg-9" pid="msg-7 msg-2" extends="web-f4v">
					<file input-file="output/trailer_360p.mp4" output-path="output/"/>
					<properties>
						<entry key="manifest-file">C:/Users/dev/workspace_j2ee/des/output/trailer_240p.f4m</entry>
						<entry key="bitrate">1000</entry>
					</properties>
				</message>
				<!-- Make result streamable - 480p -->
				<message id="msg-10" pid="msg-6 msg-9" extends="web-f4v">
					<file input-file="output/trailer_480p.mp4" output-path="output/"/>
					<properties>
						<entry key="manifest-file">C:/Users/dev/workspace_j2ee/des/output/trailer_360p.f4m</entry>
						<entry key="bitrate">1500</entry>
					</properties>
				</message>
				<!-- Make result streamable - 720p -->
				<message id="msg-11" pid="msg-1 msg-10" extends="web-f4v">
					<file input-file="output/trailer_720p.mp4" output-path="output/"/>
					<properties>
						<entry key="manifest-file">C:/Users/dev/workspace_j2ee/des/output/trailer_480p.f4m</entry>
						<entry key="bitrate">2000</entry>
					</properties>
				</message>
				<!-- Posterframe 1 -->
				<message id="msg-3" pid="msg-1" extends="web-posterframe">
					<file input-file="output/trailer_720p.mp4" output-file="output/posterframe-1.jpg"/>
					<properties>
						<entry key="ss">5</entry>
					</properties>
				</message>
				<!-- Posterframe 2 -->
				<message id="msg-4" pid="msg-1" extends="web-posterframe">
					<file input-file="output/trailer_720p.mp4" output-file="output/posterframe-2.jpg"/>
					<properties>
						<entry key="ss">10</entry>
					</properties>
				</message>
				<!-- Frames - 240 -->
				<message id="msg-5" pid="msg-1" extends="web-snapshots">
					<file input-file="output/trailer_720p.mp4" output-path="output/frames-240/f-%03d.jpg"/>
				</message>
				<!-- Frames - 120 -->
				<message id="msg-12" pid="msg-1" extends="web-snapshots">
					<file input-file="output/trailer_720p.mp4" output-path="output/frames-120/f-%03d.jpg"/>
					<properties>
						<entry key="vf">scale='120:-1'</entry>
					</properties>
				</message>
				<!-- Frames Squared - 80 -->
				<message id="msg-13" pid="msg-1" extends="web-snapshots">
					<file input-file="output/trailer_720p.mp4" output-path="output/frames-squared-80/f-%03d.jpg"/>
					<properties>
						<entry key="vf">scale='80:-1',pad='80:80:0:ceil((80-(80/a))/2)',crop='80:80'</entry>
					</properties>
				</message>
				<!-- After message has been processed, report -->
				<report>
					<!-- Define writer(s) -->
					<writer type="sql">
						<mapping>
							<field key="id">id</field>
						</mapping>
					</writer>
					<!-- Define report message strings -->
					<report-messages>
						<report-message key="CORRUPT_FILE"></report-message>
					</report-messages>
				</report>
			</dmes>

		val dmes = XmlParser.create(xml)
		println(dmes)

		// Process Scheduler
		//val scheduler = new Scheduler(conf.getInt("scheduler.max-workers"))
		//scheduler.start

		/*
    scheduler ! new Command(
        conf.getString("commands.command(0).path"))
	*/
	}

}