package com.xeline.core.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author xenron
 */
public class MessageSourceSupportRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  protected String resolvedMessage;

  protected MessageSourceSupportRuntimeException() {
  }

  public MessageSourceSupportRuntimeException(MessageSource messageSource, String code) {
    this(messageSource, code, null, null);
  }

  public MessageSourceSupportRuntimeException(MessageSource messageSource, String code, Throwable cause) {
    this(messageSource, code, null, cause);
  }

  public MessageSourceSupportRuntimeException(MessageSource messageSource, String code, Object[] args) {
    this(messageSource, code, args, null);
  }

  public MessageSourceSupportRuntimeException(MessageSource messageSource, String code, Object[] args, Throwable cause) {
    super(cause);
    Locale locale = LocaleContextHolder.getLocale();
    resolvedMessage = messageSource.getMessage(code, args, locale);
  }

  public MessageSourceSupportRuntimeException(Throwable cause) {
    super(cause);
  }

  public MessageSourceSupportRuntimeException(String message, Throwable cause) {
    super(cause);
    this.resolvedMessage = message;
  }

  /**
   * @return the resolvedMessage
   */
  public String getResolvedMessage() {
    return resolvedMessage;
  }

  @Override
  public String getMessage() {
    return resolvedMessage;
  }

}
