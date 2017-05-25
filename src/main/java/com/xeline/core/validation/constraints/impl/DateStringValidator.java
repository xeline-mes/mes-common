package com.xeline.core.validation.constraints.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.xeline.core.annotation.validation.constraints.DateString;
import com.xeline.core.util.basis.DateUtils;

/**
 * @author xenron
 */
public class DateStringValidator implements ConstraintValidator<DateString, String> {

  private String pattern;

  @Override
  public void initialize(DateString annotation) {
    this.pattern = annotation.value();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    if (value == null) {
      return true;
    } else {
      return DateUtils.nullableParse(value, pattern) != null;
    }
  }
}
