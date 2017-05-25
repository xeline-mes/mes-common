package com.xeline.core.util.io;

import java.io.Closeable;
import java.io.IOException;

/**
 * 
 *
 * @author xenron
 * @since 1.0
 *
 */
public class IOUtils {

  public static void close(Closeable closeable) {
    try {
      if (closeable != null) {
        closeable.close();
      }
    } catch (IOException ignore) {
      // no-op
    }
  }
}
