package com.xeline.core.validation.version;

import com.xeline.core.validation.version.spi.VersionCheckResult;

/**
 * @author xenron
 */
public class DefaultVersionCheckResult implements VersionCheckResult {

  private String[] names;

  private String[] errorKeys;

  public DefaultVersionCheckResult() {
  }

  public DefaultVersionCheckResult(String[] names, String[] errorKeys) {
    this.names = names;
    this.errorKeys = errorKeys;
  }

  @Override
  public boolean success() {
    return names == null || names.length == 0;
  }

  @Override
  public String[] getErrorNames() {
    return names;
  }

  @Override
  public String[] getErrorKeys() {
    return errorKeys;
  }

}
