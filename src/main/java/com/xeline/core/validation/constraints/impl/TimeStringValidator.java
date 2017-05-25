package com.xeline.core.validation.constraints.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.xeline.core.annotation.validation.constraints.TimeString;
import com.xeline.core.util.basis.DateUtils;

/**
 * @author xenron
 */
public class TimeStringValidator implements ConstraintValidator<TimeString, String> {

  private String pattern;

  @Override
  public void initialize(TimeString annotation) {
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
