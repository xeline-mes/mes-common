package com.xeline.core.exception;

/**
 * @author xenron
 */
public class DatabaseDeadLockLoserException extends DatabaseException {

  /**
   * the serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  public DatabaseDeadLockLoserException(String messageId, Object[] args, Throwable cause) {
    super(messageId, args, cause);
  }

  public DatabaseDeadLockLoserException(String messageId, Throwable cause) {
    super(messageId, cause);
  }

}
