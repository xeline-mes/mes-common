package com.xeline.core.util.basis;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.xeline.core.component.logging.AdminLogger;

/**
 * @author xenron
 */
public class HostUtils {

  private static String ipAddress;

  private static String ipAddressLower3Fig;

  public static String getIpAddressLower3fig() {

    if (ipAddressLower3Fig == null) {

      if (getIpAddress() == null) {
        return null;
      }

      int ip4thOctet;
      try {
        ip4thOctet = Integer.valueOf(ipAddress.replaceFirst("^[0-9]*\\.[0-9]*\\.[0-9]*\\.", ""));
      } catch (NumberFormatException e) {
        AdminLogger.warn(String.format("IP=%s。", ipAddress), e);
        return null;
      }

      ipAddressLower3Fig = String.format("%1$03d", ip4thOctet);
    }

    return ipAddressLower3Fig;
  }

  public static String getIpAddress() {

    if (ipAddress == null) {
      try {
        ipAddress = InetAddress.getLocalHost().getHostAddress();
      } catch (UnknownHostException e) {

        return null;
      }
    }

    return ipAddress;
  }
}
