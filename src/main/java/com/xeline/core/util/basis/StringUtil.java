package com.xeline.core.util.basis;

/**
 * @author xenron
 */
public class StringUtil {

  public static boolean isEmpty(String str) {
    return str == null || "".equals(str.trim()) ? true : false;
  }

  public static boolean isNotEmpty(String str) {
    return str == null || "".equals(str.trim()) ? false : true;
  }

}
