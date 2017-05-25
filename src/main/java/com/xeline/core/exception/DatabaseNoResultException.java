package com.xeline.core.exception;

/**
 * @author xenron
 */
public class DatabaseNoResultException extends DatabaseException {

  /**
   * the serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  public DatabaseNoResultException(String messageId, Object[] args, Throwable cause) {
    super(messageId, args, cause);
  }

  public DatabaseNoResultException(String messageId, Throwable cause) {
    super(messageId, cause);
  }

}
