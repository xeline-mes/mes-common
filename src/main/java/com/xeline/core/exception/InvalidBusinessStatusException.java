package com.xeline.core.exception;

/**
 * @author xenron
 */
public class InvalidBusinessStatusException extends ApplicationRollbackException {

  /**
   * the serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  public InvalidBusinessStatusException(String messageId, Throwable cause) {
    this(messageId, null, cause);
  }

  public InvalidBusinessStatusException(String messageId, Object[] args, Throwable cause) {
    super(messageId, args, cause);
  }
}
