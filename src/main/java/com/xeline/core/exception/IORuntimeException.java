package com.xeline.core.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * @author xenron
 */
public class IORuntimeException extends NestedRuntimeException {

  private static final long serialVersionUID = 1L;

  public IORuntimeException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public IORuntimeException(String msg) {
    super(msg);
  }

  public IORuntimeException(Throwable cause) {
    super("", cause);
  }

}
