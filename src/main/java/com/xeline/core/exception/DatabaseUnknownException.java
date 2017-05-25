package com.xeline.core.exception;

/**
 * @author xenron
 */
public class DatabaseUnknownException extends DatabaseException {

  /**
   * the serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  public DatabaseUnknownException(String messageId, Object[] args, Throwable cause) {
    super(messageId, args, cause);
  }

  public DatabaseUnknownException(String messageId, Throwable cause) {
    super(messageId, cause);
  }

}
