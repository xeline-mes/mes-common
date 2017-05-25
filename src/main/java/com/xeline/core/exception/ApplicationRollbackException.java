package com.xeline.core.exception;

import org.springframework.context.MessageSource;

/**
 * @author xenron
 */
public class ApplicationRollbackException extends MessageSourceSupportRuntimeException {

  private static final long serialVersionUID = 1L;

  private String methodName;

  private String messageId;

  private Object[] args;

  public ApplicationRollbackException(MessageSource messageSource, String methodName) {
    this(messageSource, methodName, null);
  }

  public ApplicationRollbackException(MessageSource messageSource, String methodName, Throwable cause) {
    this(messageSource, methodName, methodName, cause);
  }

  public ApplicationRollbackException(MessageSource messageSource, String methodName, String msg, Throwable cause) {
    super(messageSource, "ApplicationRollbackException", new Object[] {methodName, msg}, cause);
    this.methodName = methodName;
  }

  public ApplicationRollbackException(String messageId, Throwable cause) {
    this(messageId, null, cause);
  }

  public ApplicationRollbackException(String messageId, Object[] args, Throwable cause) {
    super(messageId, cause);
    this.messageId = messageId;
    this.args = args;
  }

  public String getMethodName() {
    return methodName;
  }

  /**
   * @return the messageId
   */
  public String getMessageId() {
    return messageId;
  }

  /**
   * @return the args
   */
  public Object[] getArgs() {
    return args;
  }

}
