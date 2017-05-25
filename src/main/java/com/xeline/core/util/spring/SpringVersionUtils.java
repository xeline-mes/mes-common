package com.xeline.core.util.spring;

import org.springframework.core.SpringVersion;
import org.springframework.util.StringUtils;

/**
 * @author xenron
 */
public class SpringVersionUtils {

  /**
   * @return check spring version is after 3.2 or not
   */
  public static boolean isLaterSpringVersion4() {
    String springVersion = SpringVersion.getVersion();
    if (StringUtils.hasText(springVersion)) {
      return springVersion.startsWith("4");
    } else {
      return false;
    }
  }

  /**
   * @return check spring version is after 4.2 or not
   */
  public static boolean isLaterSpringVersion42() {
    String springVersion = SpringVersion.getVersion();
    if (StringUtils.hasText(springVersion)) {
      return springVersion.startsWith("4.2");
    } else {
      return false;
    }
  }

}
