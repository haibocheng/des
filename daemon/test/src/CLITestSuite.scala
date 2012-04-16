/**
 * Copyright (c) 2012 Hannes Moser
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
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
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.dovigo.cli.Option
import org.dovigo.cli.Options
import org.dovigo.cli.OptionsType
import org.scalatest.junit.JUnitRunner
import org.dovigo.cli.Command

@RunWith(classOf[JUnitRunner])
class CLITestSuite extends FunSuite {

  test("Create option object with argument") {
    val option = new Option("name", true, "val")
    assert(option.toString.equals("-name val"))
  }
  
  test("Create option object without argument") {
    val option = new Option("name", false)
    assert(option.toString.equals("-name"))
  }
  
  test("Create option object with argument but without an argument value") {
    val option = new Option("name", true)
    assert(option.toString.equals("-name"))
  }
  
  test("Create option object without argument but with an argument value") {
    val option = new Option("name", false, "val")
    assert(option.toString.equals("-name"))
  }
  
  test("Create options object with 4 options") {
    val option1 = new Option("option1", true, "val")
    val option2 = new Option("option2", false)
    val option3 = new Option("option3", true)
    val option4 = new Option("option4", false, "val")
    
    val options = new Options
    options.add(option1)
    options.add(option2)
    options.add(option3)
    options.add(option4)
    
    assert(options.toString.equals(" -option1 val -option2 -option3 -option4"))
  }
  
  test("Create option POSIX style") {
    val option = new Option("name", true, "val", OptionsType.POSIX)
	assert(option.toString.equals("-name val"))
  }
  
  test("Create option GNU style") {
    val option = new Option("name", true, "val", OptionsType.GNU)
	assert(option.toString.equals("--name val"))
  }
  
  test("Create command with options") {
    val option1 = new Option("option1", true, "val")
    val option2 = new Option("option2", false)
    val option3 = new Option("option3", true)
    val option4 = new Option("option4", false, "val")
    
    val options = new Options
    options.add(option1)
    options.add(option2)
    options.add(option3)
    options.add(option4)
    
    val cmd = new Command("/usr/bin/exec", options)
    
    assert(cmd.full.equals("/usr/bin/exec -option1 val -option2 -option3 -option4"))    
  }
  
  test("Create command with options and arguments") {
    val option1 = new Option("option1", true, "val")
    val option2 = new Option("option2", false)
    val option3 = new Option("option3", true)
    val option4 = new Option("option4", false, "val")
    
    val options = new Options
    options.add(option1)
    options.add(option2)
    options.add(option3)
    options.add(option4)
    
    val cmd = new Command("/usr/bin/exec", options, List("argument1", "argument2"))
    
    assert(cmd.full.equals("/usr/bin/exec -option1 val -option2 -option3 -option4 argument1 argument2"))    
  }
  
}