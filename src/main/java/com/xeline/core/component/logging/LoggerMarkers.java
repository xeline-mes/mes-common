package com.xeline.core.component.logging;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * slf4j
 *
 * @author xenron
 */
public class LoggerMarkers {

  public static final Marker LOGIN = MarkerFactory.getMarker("login");

  public static final Marker TRACE = MarkerFactory.getMarker("trace");

  public static final Marker OPERATION = MarkerFactory.getMarker("operation");

  public static final Marker EXECUTION = MarkerFactory.getMarker("execution");

  public static final Marker MASTER = MarkerFactory.getMarker("master");

  public static final Marker STATUS = MarkerFactory.getMarker("status");

  public static final Marker USER = MarkerFactory.getMarker("user");

  public static final Marker ADMINI = MarkerFactory.getMarker("admin");

  public static final Marker DB_TRAN = MarkerFactory.getMarker("dbtran");

  public static final Marker ADDITIONAL_BUSINESS_UPDATE_INFO = MarkerFactory.getMarker("additionalBusinessUpdateInfo");

  public static final Marker DB_LOOK = MarkerFactory.getMarker("dblook");

  public static final Marker PRINT = MarkerFactory.getMarker("print");

  public static final Marker SUBSYSTEM_IF = MarkerFactory.getMarker("subsystemif");

  public static final Marker MQ_IF = MarkerFactory.getMarker("mqif");

  public static final Marker NOTICE = MarkerFactory.getMarker("notice");

  public static final Marker DOWNLOAD = MarkerFactory.getMarker("download");

  public static final Marker UPLOAD = MarkerFactory.getMarker("upload");

  public static final Marker PKG_SYSTEM = MarkerFactory.getMarker("pkgsystem");

  public static final Marker CSV = MarkerFactory.getMarker("csv");

  public static final Marker DEBUG = MarkerFactory.getMarker("debug");

}
