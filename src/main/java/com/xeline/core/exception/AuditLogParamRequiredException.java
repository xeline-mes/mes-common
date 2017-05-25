package com.xeline.core.exception;

import org.springframework.context.MessageSource;

/**
 * @author xenron
 */
public class AuditLogParamRequiredException extends MessageSourceSupportRuntimeException {

  private static final long serialVersionUID = 1L;

  private String className;

  public AuditLogParamRequiredException(MessageSource messageSource, String className) {
    super(messageSource, "AuditLogParamRequiredException", new Object[] {className});
    this.className = className;
  }

  public String getClassName() {
    return className;
  }

}
