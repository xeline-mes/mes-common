package com.xeline.core.exception;

/**
 * @author xenron
 */
public class DatabaseNonUniqueResultException extends DatabaseException {

  /**
   * the serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  public DatabaseNonUniqueResultException(String messageId, Object[] args, Throwable cause) {
    super(messageId, args, cause);
  }

  public DatabaseNonUniqueResultException(String messageId, Throwable cause) {
    super(messageId, cause);
  }

}
