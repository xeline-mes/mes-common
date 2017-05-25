package com.xeline.core.exception;

import org.springframework.context.MessageSource;

/**
 * @author xenron
 */
public class VersionCheckKeyRequiredException extends MessageSourceSupportRuntimeException {

  private static final long serialVersionUID = 1L;

  private String className;

  public VersionCheckKeyRequiredException(MessageSource messageSource, String className) {
    super(messageSource, "VersionCheckKeyRequiredException", new Object[] {className});
    this.className = className;
  }

  public String getClassName() {
    return className;
  }

}
