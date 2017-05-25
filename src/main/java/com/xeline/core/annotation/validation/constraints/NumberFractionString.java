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
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern.Flag;

import com.xeline.core.annotation.validation.constraints.standard.ValidPattern;

@Documented
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@ValidPattern(regexp = "^([0-9]\\d*|0)(\\.\\d+)?$")
@ReportAsSingleViolation
public @interface NumberFractionString {

  String message() default "{com.xeline.annotation.validation.constraints.NumberFractionString.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String aliasName() default "";

  @OverridesAttribute(constraint = ValidPattern.class, name = "allowEmpty")
  boolean allowEmpty() default true;

  @OverridesAttribute(constraint = ValidPattern.class, name = "flags")
  Flag[] flags() default {};

  /**
   * Defines several {@code @NumberFractionString} annotations on the same element.
   */
  @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
  @Retention(RUNTIME)
  @Documented
  public @interface List {
    NumberFractionString[] value();
  }

}
