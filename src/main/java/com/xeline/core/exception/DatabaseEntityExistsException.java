package com.xeline.core.exception;

/**
 * @author xenron
 */
public class DatabaseEntityExistsException extends DatabaseException {

  /**
   * the serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  public DatabaseEntityExistsException(String messageId, Object[] args, Throwable cause) {
    super(messageId, args, cause);
  }

  public DatabaseEntityExistsException(String messageId, Throwable cause) {
    super(messageId, cause);
  }

}
