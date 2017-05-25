package com.xeline.core.component.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xenron
 */
public class AdminLogger {

  /** Logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(LoggerMarkers.ADMINI.toString());

  /**
   * info
   */
  public static void info(String format, Object... args) {
    LOGGER.info(LoggerMarkers.ADMINI, format, args);
  }

  /**
   * warn
   */
  public static void warn(String format, Object... args) {
    LOGGER.warn(LoggerMarkers.ADMINI, format, args);
  }

  /**
   * warn
   */
  public static void warn(String msg, Throwable t) {
    LOGGER.warn(LoggerMarkers.ADMINI, msg, t);
  }

  /**
   * error
   */
  public static void error(String format, Object... args) {
    LOGGER.error(LoggerMarkers.ADMINI, format, args);
  }

  /**
   * error
   */
  public static void error(String msg, Throwable t) {
    LOGGER.error(LoggerMarkers.ADMINI, msg, t);
  }
}
