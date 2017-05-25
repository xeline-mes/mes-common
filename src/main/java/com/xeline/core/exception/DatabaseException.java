package com.xeline.core.exception;

/**
 * @author xenron
 */
public class DatabaseException extends MiddlewareException {

  /**
   * the serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  public DatabaseException(String messageId, Object[] args, Throwable cause) {
    super(messageId, args, cause);
  }

  public DatabaseException(String messageId, Throwable cause) {
    super(messageId, cause);
  }
}
