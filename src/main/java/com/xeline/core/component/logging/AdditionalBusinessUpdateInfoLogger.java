package com.xeline.core.component.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xenron
 */
public class AdditionalBusinessUpdateInfoLogger {

  /** Logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(LoggerMarkers.ADDITIONAL_BUSINESS_UPDATE_INFO.toString());

  /**
   * info log
   */
  public static void info(String format, Object... args) {
    LOGGER.info(LoggerMarkers.ADDITIONAL_BUSINESS_UPDATE_INFO, format, args);
  }
}
