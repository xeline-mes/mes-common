package com.xeline.core.util.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GZIPに関するユーティリティです。
 *
 * @author xenron
 * @since 1.0
 *
 */
public class GZIPUtils {

  public static InputStream newInputStream(InputStream is) {
    try {
      return new GZIPInputStream(is);
    } catch (IOException e) {
      return null;
    }
  }

  public static GZIPOutputStream newOutputStream(OutputStream out) {
    try {
      return new GZIPOutputStream(out);
    } catch (IOException e) {
      return null;
    }
  }

  public static void finish(GZIPOutputStream out) {
    try {
      out.finish();
    } catch (IOException e) {
      return;
    }
  }

}
