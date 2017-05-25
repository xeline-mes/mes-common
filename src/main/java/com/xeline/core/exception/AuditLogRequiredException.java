package com.xeline.core.exception;

import org.springframework.context.MessageSource;

/**
 * @author xenron
 */
public class AuditLogRequiredException extends MessageSourceSupportRuntimeException {

  private static final long serialVersionUID = 1L;

  private String className;

  public AuditLogRequiredException(MessageSource messageSource, String className) {
    super(messageSource, "AuditLogRequiredException", new Object[] {className});
    this.className = className;
  }

  public String getClassName() {
    return className;
  }

}
