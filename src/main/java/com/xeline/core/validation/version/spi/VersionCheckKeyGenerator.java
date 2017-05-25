package com.xeline.core.validation.version.spi;

import java.util.List;

/**
 * @author xenron
 */
public interface VersionCheckKeyGenerator {

  public List<String> getVersionFields(Object dto);

  public String generateKey(Object dto);

  public String generateKey(Object dto, List<String> fieldNames);

}
