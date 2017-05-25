package com.xeline.core.annotation.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.xeline.core.validation.constraints.impl.NullOrEmptyValidator;

@Documented
@Constraint(validatedBy = {NullOrEmptyValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface NullOrEmpty {

  String message() default "{com.xeline.annotation.validation.constraints.NullOrEmpty.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  boolean allowEmpty() default true;

  String aliasName() default "";

  /**
   * Defines several {@code @NullOrEmpty} annotations on the same element.
   */
  @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
  @Retention(RUNTIME)
  @Documented
  @interface List {
    NullOrEmpty[] value();
  }

}
