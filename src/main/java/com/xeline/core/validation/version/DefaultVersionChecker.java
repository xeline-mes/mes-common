package com.xeline.core.validation.version;

/**
 * @author xenron
 */
public class DefaultVersionChecker extends AbstractVersionChecker<Long> {

  @Override
  protected boolean validateVersion(Long version1, Long version2) {
    return version1.longValue() == version2.longValue();
  }

  @Override
  protected Class<?> getAssignableClass() {
    return Long.class;
  }

}
