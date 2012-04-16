package org.dovigo.log
import org.slf4j.LoggerFactory

/**
 * Logging trait, wraps slf4j
 *
 * @author Hannes Moser
 * @version 0.1
 * @since 0.1
 */
trait Logging {

  var log = LoggerFactory.getLogger(getClass)

  def debug(msg: => String) = if (log.isDebugEnabled) log.debug(msg)
  def info(msg: => String) = if (log.isDebugEnabled) log.info(msg)
  def warn(msg: => String) = if (log.isDebugEnabled) log.warn(msg)
  def error(msg: => String) = if (log.isDebugEnabled) log.error(msg)

}