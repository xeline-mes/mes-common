package com.xeline.core.annotation.validation.constraints.standard;

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
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

import com.xeline.core.annotation.validation.constraints.NullOrEmpty;

@Documented
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@NullOrEmpty
@Min(0)
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
public @interface ValidMin {

  String message() default "{com.xeline.annotation.validation.constraints.standard.ValidMin.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String aliasName() default "";

  @OverridesAttribute(constraint = NullOrEmpty.class, name = "allowEmpty")
  boolean allowEmpty() default true;

  @OverridesAttribute(constraint = Min.class, name = "value")
  long value();

  /**
   * Defines several {@code ValidMin} annotations on the same element.
   */
  @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
  @Retention(RUNTIME)
  @Documented
  @interface List {
    ValidMin[] value();
  }

}
