package com.xeline.core.exception;

/**
 * @author xenron
 */
public class DatabaseTimeoutException extends DatabaseException {

  /**
   * the serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  public DatabaseTimeoutException(String messageId, Object[] args, Throwable cause) {
    super(messageId, args, cause);
  }

  public DatabaseTimeoutException(String messageId, Throwable cause) {
    super(messageId, cause);
  }

}
