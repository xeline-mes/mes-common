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

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

import com.xeline.core.validation.constraints.impl.TimeStringValidator;

@Documented
@Constraint(validatedBy = {TimeStringValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@NullOrEmpty
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
public @interface TimeString {

  String message() default "{com.xeline.annotation.validation.constraints.TimeString.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String aliasName() default "";

  @OverridesAttribute(constraint = NullOrEmpty.class, name = "allowEmpty")
  boolean allowEmpty() default true;

  String value() default "HHmm";

  String displayValue() default "HH:mm";

  /**
   * Defines several {@code @TimeString} annotations on the same element.
   */
  @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
  @Retention(RUNTIME)
  @Documented
  public @interface List {
    TimeString[] value();
  }

}
