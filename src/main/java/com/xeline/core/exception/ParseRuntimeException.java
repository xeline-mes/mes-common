package com.xeline.core.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * @author xenron
 */
public class ParseRuntimeException extends NestedRuntimeException {

  private static final long serialVersionUID = 1L;

  public ParseRuntimeException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public ParseRuntimeException(String msg) {
    super(msg);
  }

  public ParseRuntimeException(Throwable cause) {
    super("", cause);
  }

}
