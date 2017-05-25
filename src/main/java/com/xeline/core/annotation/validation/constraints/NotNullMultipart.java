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

import com.xeline.core.validation.constraints.impl.NotNullMultipartValidator;

@Documented
@Constraint(validatedBy = {NotNullMultipartValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface NotNullMultipart {

  String message() default "{com.xeline.annotation.validation.constraints.NotNullMultipart.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String aliasName() default "";

  boolean allowArrayOrCollectionEmpty() default false;

  /**
   * Defines several {@code @NotNullMultipart} annotations on the same element.
   */
  @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
  @Retention(RUNTIME)
  @Documented
  @interface List {
    NotNullMultipart[] value();
  }

}
