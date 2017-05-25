package com.xeline.core.util.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.xeline.core.exception.IORuntimeException;

/**
 * 
 *
 * @author xenron
 * @since 1.0
 *
 */
public class FileOutputStreamUtils {

  // for hotReloading.
  // IORuntimeException ex = null;

  public static FileOutputStream create(File file) throws IORuntimeException {
    try {
      return new FileOutputStream(file);
    } catch (IOException e) {
      // throw new IORuntimeException(e);
    }
    return null;
  }

}
