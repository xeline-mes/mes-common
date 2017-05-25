package com.xeline.core.util.basis;

/**
 * @author xenron
 */
public class ExceptionUtils {

  public static <T extends Throwable> T getCause(Throwable t, Class<T> targetExceptionClass) {
    if (t == null) {
      return null;
    }
    Throwable throwable = t.getCause();
    if (throwable == null) {
      return null;
    } else {
      if (targetExceptionClass.isAssignableFrom(throwable.getClass())) {
        return targetExceptionClass.cast(throwable);
      } else {
        return getCause(throwable, targetExceptionClass);
      }
    }
  }

}
