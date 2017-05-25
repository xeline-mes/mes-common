package com.xeline.core.component.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xenron
 */
public class DebugLogger {

  /** Logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(LoggerMarkers.DEBUG.toString());

  /**
   * debug
   */
  public static void debug(String format, Object... args) {
    LOGGER.debug(LoggerMarkers.DEBUG, format, args);
  }

  /**
   * debug
   */
  public static void debug(String msg, Throwable t) {
    LOGGER.debug(LoggerMarkers.DEBUG, msg, t);
  }

  /**
   * trace
   */
  public static void trace(String format, Object... args) {
    LOGGER.trace(LoggerMarkers.DEBUG, format, args);
  }

  /**
   * trace
   */
  public static void trace(String msg, Throwable t) {
    LOGGER.trace(LoggerMarkers.DEBUG, msg, t);
  }
}
