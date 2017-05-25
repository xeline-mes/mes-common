package com.xeline.core.exception;

/**
 * @author xenron
 */
public class IllegalArgumentExceptionEx extends IllegalArgumentException {

  /**
   * the serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  private String messageId;

  private Object[] args;

  public IllegalArgumentExceptionEx(String messageId, Throwable cause) {
    this(messageId, null, cause);
  }

  public IllegalArgumentExceptionEx(String messageId, Object[] args, Throwable cause) {
    super(messageId, cause);
    this.messageId = messageId;
    this.args = args;
  }

  public String getMessageId() {
    return messageId;
  }

  public Object[] getArgs() {
    return args;
  }
}
