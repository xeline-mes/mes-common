package com.xeline.core.component.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xenron
 */
public class CsvLogger {

  /** Logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(LoggerMarkers.CSV.toString());

  /**
   * info
   */
  public static void info(String format, Object... args) {
    LOGGER.info(LoggerMarkers.CSV, format, args);
  }

}
