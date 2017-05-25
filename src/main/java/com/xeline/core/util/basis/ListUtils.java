package com.xeline.core.util.basis;

import java.util.List;

/**
 * list util
 *
 * @author xenron
 */
public class ListUtils {

  public static boolean isEmpty(List<?> list) {
    if (list == null || list.size() == 0) {
      return true;
    } else {
      return false;
    }
  }

  public static int getSize(List<?> list) {
    if (isEmpty(list)) {
      return 0;
    } else {
      return list.size();
    }
  }
}
