package com.xeline.core.exception;

/**
 * @author xenron
 */
public class FatalException extends RuntimeException {

  private String messageId;

  private Object[] args;

  private static final long serialVersionUID = 1L;

  public FatalException(String messageId, Throwable cause) {
    this(messageId, null, cause);
  }

  public FatalException(String messageId, Object[] args, Throwable cause) {
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
