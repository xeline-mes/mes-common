package com.xeline.core.exception;

import org.springframework.context.MessageSource;

/**
 * @author xenron
 */
public class DuplicateVersionCheckKeyException extends MessageSourceSupportRuntimeException {

  private static final long serialVersionUID = 1L;

  private String key;

  private String className;

  public DuplicateVersionCheckKeyException(MessageSource messageSource, String key, String className) {
    super(messageSource, "DuplicateVersionCheckKeyException", new Object[] {key, className});
    this.key = key;
    this.className = className;
  }

  public String getKey() {
    return key;
  }

  public String getClassName() {
    return className;
  }

}
