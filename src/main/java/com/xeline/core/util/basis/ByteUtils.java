package com.xeline.core.util.basis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.util.StringUtils;

import com.xeline.core.component.logging.AdminLogger;
import com.xeline.core.exception.ClassNotFoundRuntimeException;
import com.xeline.core.exception.IORuntimeException;
import com.xeline.core.util.io.GZIPUtils;
import com.xeline.core.util.io.ObjectStreamUtils;

/**
 * @author xenron
 */
public final class ByteUtils {

  private static final int BYTE_ARRAY_SIZE = 8 * 1024;

  public static Byte toByte(Object o) {
    if (o == null) {
      return null;
    } else if (o.getClass() == Byte.class) {
      return (Byte) o;
    } else if (o instanceof Number) {
      return Byte.valueOf(((Number) o).byteValue());
    } else if (o.getClass() == String.class) {
      String s = (String) o;
      if (!StringUtils.hasText(s)) {
        return null;
      }
      return Byte.valueOf(s);
    } else if (o.getClass() == Boolean.class) {
      return ((Boolean) o).booleanValue() ? Byte.valueOf((byte) 1) : Byte.valueOf((byte) 0);
    } else {
      return Byte.valueOf(o.toString());
    }
  }

  public static byte toPrimitiveByte(Object o) {
    Byte b = toByte(o);
    if (b == null) {
      return 0;
    }
    return b.byteValue();
  }

  public static byte[] toByteArray(Object o) {
    if (o == null) {
      return null;
    }
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream(BYTE_ARRAY_SIZE); ObjectOutputStream oos = ObjectStreamUtils.newOutputStream(baos);) {
      ObjectStreamUtils.writeObject(oos, o);
      return baos.toByteArray();
    } catch (IOException e) {
      AdminLogger.error(e.getMessage());
    }
    return null;
  }

  public static byte[] toGZIPByteArray(Object o) {
    if (o == null) {
      return null;
    }
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream(BYTE_ARRAY_SIZE); GZIPOutputStream out = GZIPUtils.newOutputStream(baos); ObjectOutputStream oos = ObjectStreamUtils.newOutputStream(
        out);) {
      ObjectStreamUtils.writeObject(oos, o);
      GZIPUtils.finish(out);
      return baos.toByteArray();
    } catch (IOException e) {
      AdminLogger.error(e.getMessage());
    }
    return null;

  }

  public static <T> T toObject(byte[] bytes) {
    return toObject(bytes, Thread.currentThread().getContextClassLoader());
  }

  public static <T> T toObject(byte[] bytes, final ClassLoader classLoader) throws NullPointerException {
    if (bytes == null) {
      return null;
    }
    if (classLoader == null) {
      throw new NullPointerException("The classLoader parameter is null.");
    }
    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
    ObjectInputStream ois = ObjectStreamUtils.newInputStream(bais, classLoader);
    return readObject(ois);
  }

  public static <T> T toObjectFromGZIPByteArray(byte[] bytes) {
    return toObjectFromGZIPByteArray(bytes, Thread.currentThread().getContextClassLoader());
  }

  public static <T> T toObjectFromGZIPByteArray(byte[] bytes, final ClassLoader classLoader) throws NullPointerException {
    if (bytes == null) {
      return null;
    }
    if (classLoader == null) {
      throw new NullPointerException("The classLoader parameter is null.");
    }
    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
    InputStream in = GZIPUtils.newInputStream(bais);
    ObjectInputStream ois = ObjectStreamUtils.newInputStream(in, classLoader);
    return readObject(ois);
  }

  @SuppressWarnings("unchecked")
  protected static <T> T readObject(ObjectInputStream ois) {
    try {
      Object o = null;
      try {
        o = ois.readObject();
      } finally {
        ois.close();
      }
      return (T) o;
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundRuntimeException(e);
    }

  }

  public static byte[][] split(byte[] bytes, int size) throws NullPointerException, IllegalArgumentException {
    if (bytes == null) {
      throw new NullPointerException("The bytes parameter is null.");
    }
    if (size <= 0) {
      throw new IllegalArgumentException("The size parameter must be more than 0.");
    }
    int num = bytes.length / size;
    int mod = bytes.length % size;
    byte[][] ret = mod > 0 ? new byte[num + 1][0] : new byte[num][0];
    for (int i = 0; i < num; i++) {
      ret[i] = new byte[size];
      System.arraycopy(bytes, i * size, ret[i], 0, size);
    }
    if (mod > 0) {
      ret[num] = new byte[mod];
      System.arraycopy(bytes, num * size, ret[num], 0, mod);
    }
    return ret;
  }

  public static byte[] join(byte[][] bytesArray) {
    if (bytesArray == null) {
      throw new NullPointerException("The bytesArray parameter is null.");
    }
    int length = 0;
    for (byte[] bytes : bytesArray) {
      length += bytes.length;
    }
    byte[] ret = new byte[length];
    int pos = 0;
    for (byte[] bytes : bytesArray) {
      System.arraycopy(bytes, 0, ret, pos, bytes.length);
      pos += bytes.length;
    }
    return ret;
  }

  private ByteUtils() {
  }
}
