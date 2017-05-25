package com.xeline.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditLog {

  AuditLogStyle style() default AuditLogStyle.SINGLE;

  String separator() default "|";

  public enum AuditLogStyle {
    SINGLE, MULTILINE
  }

}
