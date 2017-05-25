package com.xeline.core.exception;

/**
 * @author xenron
 */
public class DatabaseOptimisticLockException extends DatabaseException {

  /**
   * the serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  public DatabaseOptimisticLockException(String messageId, Object[] args, Throwable cause) {
    super(messageId, args, cause);
  }

  public DatabaseOptimisticLockException(String messageId, Throwable cause) {
    super(messageId, cause);
  }

}
