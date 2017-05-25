package com.xeline.core.util.basis;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationException;

import com.xeline.core.exception.MiddlewareException;

/**
 * Serialization
 *
 * @author xenron
 */
public class SerializationUtils {

  public static <T extends Serializable> T clone(final T object) {
    try {
      T result = SerializationUtils.clone(object);
      return result;
    } catch (final SerializationException ex) {
      throw new MiddlewareException("", ex.getCause());
    }

  }
}
