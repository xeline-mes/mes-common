package com.xeline.core.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * @author xenron
 */
public class ClassNotFoundRuntimeException extends NestedRuntimeException {

  private static final long serialVersionUID = 1L;

  public ClassNotFoundRuntimeException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public ClassNotFoundRuntimeException(String msg) {
    super(msg);
  }

  public ClassNotFoundRuntimeException(Throwable cause) {
    super("", cause);
  }

}
