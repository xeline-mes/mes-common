package com.xeline.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
 * @author xenron
 */
public class ClassLoaderAwareObjectInputStream extends ObjectInputStream {

  private ClassLoader classLoader;

  public ClassLoaderAwareObjectInputStream(InputStream is, ClassLoader classLoader) throws IOException {
    super(is);
    this.classLoader = classLoader;
  }

  @Override
  protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
    String name = desc.getName();
    try {
      return Class.forName(name, false, classLoader);
    } catch (ClassNotFoundException ex) {
      return super.resolveClass(desc);
    }
  }

}
