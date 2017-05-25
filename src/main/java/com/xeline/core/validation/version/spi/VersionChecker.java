package com.xeline.core.validation.version.spi;

import java.util.List;

/**
 * @author xenron
 */
public interface VersionChecker {

  public VersionCheckResult validate(Object o1, Object o2);

  public VersionCheckResult validateList(String[] selectedArray, List<?> list, List<?> outer);

}
