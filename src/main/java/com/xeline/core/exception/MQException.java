package com.xeline.core.exception;

/**
 * @author xenron
 */
public class MQException extends MiddlewareException {

  /**
   * the serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  public MQException(String messageId, Object[] args, Throwable cause) {
    super(messageId, args, cause);
  }

  public MQException(String messageId, Throwable cause) {
    super(messageId, cause);
  }
}
