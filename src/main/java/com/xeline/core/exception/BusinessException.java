package com.xeline.core.exception;

/**
 * @author xenron
 */
public class BusinessException extends ApplicationRollbackException {

  /**
   * the serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  public BusinessException(String messageId, Throwable cause) {
    this(messageId, null, cause);
  }

  public BusinessException(String messageId, Object[] args, Throwable cause) {
    super(messageId, args, cause);
  }

}
