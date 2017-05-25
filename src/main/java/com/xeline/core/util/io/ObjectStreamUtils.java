package com.xeline.core.util.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.xeline.core.io.ClassLoaderAwareObjectInputStream;

/**
 * ObjectInputStreamに関するユーティリティです。
 *
 * @author xenron
 * @since 1.0
 *
 */
public class ObjectStreamUtils {

  public static ObjectInputStream newInputStream(InputStream is) {
    try {
      return new ObjectInputStream(is);
    } catch (IOException e) {
      return null;
    }
  }

  public static ObjectInputStream newInputStream(InputStream is, ClassLoader classLoader) {
    try {
      return new ClassLoaderAwareObjectInputStream(is, classLoader);
    } catch (IOException e) {
      return null;
    }
  }

  public static Object readObject(ObjectInputStream ois) {
    try {
      return ois.readObject();
    } catch (IOException e) {
      return null;
    } catch (ClassNotFoundException e) {
      return null;
    }
  }

  public static ObjectOutputStream newOutputStream(OutputStream out) {
    try {
      return new ObjectOutputStream(out);
    } catch (IOException e) {
      return null;
    }
  }

  public static void writeObject(ObjectOutputStream out, Object target) {
    try {
      out.writeObject(target);
    } catch (IOException e) {
      return;
    }
  }

}
