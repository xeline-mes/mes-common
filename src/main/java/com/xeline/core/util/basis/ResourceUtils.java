package com.xeline.core.util.basis;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import com.xeline.core.exception.IORuntimeException;
import com.xeline.core.exception.ResourceNotFoundException;

/**
 * @author xenron
 */
public class ResourceUtils {

  public static int DEFAULT_BUFFER_SIZE = 8192;

  public static String DEFAULT_ENCODING = "UTF-8";

  /** Pseudo URL prefix for loading from the class path: "classpath:" */
  public static final String CLASSPATH_URL_PREFIX = org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX;

  /** URL prefix for loading from the file system: "file:" */
  public static final String FILE_URL_PREFIX = org.springframework.util.ResourceUtils.FILE_URL_PREFIX;

  /** URL protocol for a file in the file system: "file" */
  public static final String URL_PROTOCOL_FILE = org.springframework.util.ResourceUtils.URL_PROTOCOL_FILE;

  /** URL protocol for an entry from a jar file: "jar" */
  public static final String URL_PROTOCOL_JAR = org.springframework.util.ResourceUtils.URL_PROTOCOL_JAR;

  /** URL protocol for an entry from a zip file: "zip" */
  public static final String URL_PROTOCOL_ZIP = org.springframework.util.ResourceUtils.URL_PROTOCOL_ZIP;

  /** URL protocol for an entry from a JBoss jar file: "vfszip" */
  public static final String URL_PROTOCOL_VFSZIP = org.springframework.util.ResourceUtils.URL_PROTOCOL_VFSZIP;

  /** URL protocol for a JBoss VFS resource: "vfs" */
  public static final String URL_PROTOCOL_VFS = org.springframework.util.ResourceUtils.URL_PROTOCOL_VFS;

  /** URL protocol for an entry from a WebSphere jar file: "wsjar" */
  public static final String URL_PROTOCOL_WSJAR = org.springframework.util.ResourceUtils.URL_PROTOCOL_WSJAR;

  /** Separator between JAR URL and file path within the JAR */
  public static final String JAR_URL_SEPARATOR = org.springframework.util.ResourceUtils.JAR_URL_SEPARATOR;

  // @SuppressWarnings("unused")
  // private IORuntimeException ex1 = null;

  @SuppressWarnings("unused")
  private ResourceNotFoundException ex2 = null;

  public static Resource getResource(String location, ResourceLoader resourceLoader) {
    return resourceLoader.getResource(location);
  }

  public static Resource getFileResource(String location, ResourceLoader resourceLoader) {
    return resourceLoader.getResource(FILE_URL_PREFIX + location);
  }

  public static String asFileResourcePath(String location) {
    return FILE_URL_PREFIX + location;
  }

  public static File getFile(String location, ResourceLoader resourceLoader) {
    Resource resource = getResource(location, resourceLoader);
    return getFile(resource);
  }

  public static boolean existResource(String location, ResourceLoader resourceLoader) {
    Resource resource = getResource(location, resourceLoader);
    return resource != null && resource.exists();
  }

  public static boolean existFileResource(String location, ResourceLoader resourceLoader) {
    Resource resource = getFileResource(location, resourceLoader);
    return resource != null && resource.exists();
  }

  public static boolean existFile(String location, ResourceLoader resourceLoader) {
    return existResource(location, resourceLoader);
  }

  public static boolean mkdirs(File file) {
    return file.getParentFile().mkdirs();
  }

  public static boolean isOpen(Resource resource) {
    return resource.isOpen();
  }

  public static boolean isOpen(File file) {
    FileSystemResource resource = new FileSystemResource(file);
    return resource.isOpen();
  }

  public static boolean isReadable(Resource resource) {
    return resource.isReadable();
  }

  public static boolean isReadable(File file) {
    FileSystemResource resource = new FileSystemResource(file);
    return resource.isReadable();
  }

  public static boolean isWritable(File file) {
    FileSystemResource resource = new FileSystemResource(file);
    return resource.isWritable();
  }

  public static long getContentLength(Resource resource) {
    try {
      return resource.contentLength();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static long getContentLength(File file) {
    return file.length();
  }

  public static InputStream getInputStream(Resource resource) {
    return getInputStream(resource, DEFAULT_BUFFER_SIZE);
  }

  public static InputStream getInputStream(Resource resource, int size) {
    try {
      return wrap(resource.getInputStream(), size);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static InputStream getInputStream(File file) {
    return getInputStream(file, DEFAULT_BUFFER_SIZE);
  }

  public static InputStream getInputStream(File file, int size) {
    try {
      return wrap(new FileSystemResource(file).getInputStream(), size);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static BufferedReader getReader(Resource resource) {
    return getReader(resource, DEFAULT_ENCODING, DEFAULT_BUFFER_SIZE);
  }

  public static BufferedReader getReader(Resource resource, String charsetName) {
    return getReader(resource, charsetName, DEFAULT_BUFFER_SIZE);
  }

  public static BufferedReader getReader(Resource resource, String charsetName, int size) {
    try {
      return wrap(new InputStreamReader(resource.getInputStream(), charsetName), size);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static BufferedReader getReader(File file) {
    return getReader(file, DEFAULT_ENCODING, DEFAULT_BUFFER_SIZE);
  }

  public static BufferedReader getReader(File file, String charsetName) {
    return getReader(file, charsetName, DEFAULT_BUFFER_SIZE);
  }

  public static BufferedReader getReader(File file, String charsetName, int size) {
    try {
      return wrap(new InputStreamReader(new FileSystemResource(file).getInputStream(), charsetName), size);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static OutputStream getOutputStream(Resource resource) {
    return getOutputStream(resource, DEFAULT_BUFFER_SIZE);
  }

  public static OutputStream getOutputStream(Resource resource, int size) {
    File file = getFile(resource);
    return getOutputStream(file, size);
  }

  public static OutputStream getOutputStream(File file) {
    return getOutputStream(file, DEFAULT_BUFFER_SIZE);
  }

  public static BufferedWriter getWriter(File file) {
    return getWriter(file, DEFAULT_ENCODING, DEFAULT_BUFFER_SIZE);
  }

  public static BufferedWriter getWriter(File file, String charsetName) {
    return getWriter(file, charsetName, DEFAULT_BUFFER_SIZE);
  }

  public static BufferedWriter getWriter(File file, String charsetName, int size) {
    try {
      return wrap(new OutputStreamWriter(new FileSystemResource(file).getOutputStream(), charsetName), size);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static OutputStream getOutputStream(File file, int size) {
    try {
      return wrap(new FileSystemResource(file).getOutputStream(), size);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static File createTempFile(String prefix, String suffix) {
    try {
      return File.createTempFile(prefix, suffix);
    } catch (IOException e) {
      throw new ResourceNotFoundException(e.getMessage(), "createTempFile", e);
    }
  }

  public static File createTempFile(String prefix, String suffix, File directory) {
    try {
      return File.createTempFile(prefix, suffix, directory);
    } catch (IOException e) {
      throw new ResourceNotFoundException(e.getMessage(), "createTempFile", e);
    }
  }

  public static File createRelativeFile(Resource resource, String path) {
    return createRelativeFile(resource, path, true);
  }

  public static File createRelativeFile(Resource resource, String path, boolean mkdir) {
    File parent = getFile(resource);
    if (mkdir) {
      parent.mkdirs();
    }
    return createRelativeFile(parent, path);
  }

  public static File createRelativeFile(File parent, String path) {
    return createRelativeFile(parent, path, true);
  }

  public static File createRelativeFile(File parent, String path, boolean mkdir) {
    if (mkdir) {
      parent.mkdirs();
    }
    return new File(parent, path);
  }

  public static Resource createRelativeResource(Resource resource, String path) {
    try {
      return resource.createRelative(path);
    } catch (IOException e) {
      throw new ResourceNotFoundException(e.getMessage(), path, e);
    }
  }

  public static String createRelativePath(Resource resource, String path) {
    File parent = getFile(resource);
    return createRelativePath(parent, path);
  }

  public static String createRelativePath(File parent, String path) {
    return new File(parent, path).getPath();
  }

  public static File getFile(Resource resource) {
    try {
      return resource.getFile();
    } catch (IOException e) {
      throw new ResourceNotFoundException(e.getMessage(), resource.getFilename(), e);
    }
  }

  public static int copy(Resource in, File out) {
    try {
      return FileCopyUtils.copy(in.getInputStream(), getOutputStream(out));
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static int copy(Resource in, OutputStream out) {
    try {
      return FileCopyUtils.copy(in.getInputStream(), wrap(out));
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static int copy(File in, File out) {
    try {
      return FileCopyUtils.copy(in, out);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static void copy(byte[] in, File out) {
    try {
      FileCopyUtils.copy(in, out);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static byte[] copyToByteArray(File in) {
    try {
      return FileCopyUtils.copyToByteArray(in);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static int copy(InputStream in, OutputStream out) {
    try {
      return FileCopyUtils.copy(wrap(in), wrap(out));
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static void copy(byte[] in, OutputStream out) {
    try {
      FileCopyUtils.copy(in, wrap(out));
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static byte[] copyToByteArray(InputStream in) {
    try {
      return FileCopyUtils.copyToByteArray(wrap(in));
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static int copy(Reader in, Writer out) {
    try {
      return FileCopyUtils.copy(wrap(in), wrap(out));
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static void copy(String in, Writer out) {
    try {
      FileCopyUtils.copy(in, wrap(out));
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static String copyToString(Reader in) {
    try {
      return FileCopyUtils.copyToString(wrap(in));
    } catch (IOException e) {
      throw new IORuntimeException(e);
    }
  }

  public static InputStream wrap(InputStream in) {
    return wrap(in, DEFAULT_BUFFER_SIZE);
  }

  public static InputStream wrap(InputStream in, int size) {
    if (in instanceof BufferedInputStream) {
      return in;
    } else {
      return new BufferedInputStream(in, size);
    }
  }

  public static BufferedReader wrap(Reader reader) {
    return wrap(reader, DEFAULT_BUFFER_SIZE);
  }

  public static BufferedReader wrap(Reader reader, int size) {
    if (reader instanceof BufferedReader) {
      return (BufferedReader) reader;
    } else {
      return new BufferedReader(reader, size);
    }
  }

  public static OutputStream wrap(OutputStream out) {
    return wrap(out, DEFAULT_BUFFER_SIZE);
  }

  public static OutputStream wrap(OutputStream out, int size) {
    if (out instanceof BufferedOutputStream) {
      return out;
    } else {
      return new BufferedOutputStream(out, size);
    }
  }

  public static BufferedWriter wrap(Writer writer) {
    return wrap(writer, DEFAULT_BUFFER_SIZE);
  }

  public static BufferedWriter wrap(Writer writer, int size) {
    if (writer instanceof BufferedWriter) {
      return (BufferedWriter) writer;
    } else {
      return new BufferedWriter(writer, size);
    }
  }

}
