package com.xeline.core.exception;

/**
 * @author xenron
 */
public class MiddlewareException extends ApplicationRollbackException {

  /**
   * the serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  public MiddlewareException(String messageId, Throwable cause) {
    this(messageId, null, cause);
  }

  public MiddlewareException(String messageId, Object[] args, Throwable cause) {
    super(messageId, args, cause);
  }
}
