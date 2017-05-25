package com.xeline.core.exception;

import org.springframework.context.MessageSource;

/**
 * @author xenron
 */
public class ResourceNotFoundException extends MessageSourceSupportRuntimeException {

  private static final long serialVersionUID = 1L;

  private String path;

  public ResourceNotFoundException(MessageSource messageSource, String path, Throwable cause) {
    super(messageSource, "ResourceNotFoundException", new Object[] {path, cause.getMessage()}, cause);
    this.path = path;
  }

  public ResourceNotFoundException(String message, String path, Throwable cause) {
    super(cause);
    this.resolvedMessage = message;
    this.path = path;
  }

  public String getPath() {
    return path;
  }

}
