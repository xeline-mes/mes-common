package com.xeline.core.validation.version.spi;

/**
 * @author xenron
 */
public interface VersionCheckResult {

  public boolean success();

  public String[] getErrorNames();

  public String[] getErrorKeys();

}
