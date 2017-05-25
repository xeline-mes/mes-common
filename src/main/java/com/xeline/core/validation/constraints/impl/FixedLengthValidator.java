package com.xeline.core.validation.constraints.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.xeline.core.annotation.validation.constraints.FixedLength;

/**
 * @author xenron
 */
public class FixedLengthValidator implements ConstraintValidator<FixedLength, CharSequence> {

  private int length;

  @Override
  public void initialize(FixedLength annotation) {
    this.length = annotation.value();
  }

  @Override
  public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

    if (value == null) {
      return true;
    } else {
      int l = value.length();
      return length == l;
    }
  }
}
