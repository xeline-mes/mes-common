package com.xeline.core.validation.constraints.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.xeline.core.annotation.validation.constraints.NullOrEmpty;

/**
 * @author xenron
 */
public class NullOrEmptyValidator implements ConstraintValidator<NullOrEmpty, Object> {

  private boolean allowEmpty;

  @Override
  public void initialize(NullOrEmpty constraintAnnotation) {
    allowEmpty = constraintAnnotation.allowEmpty();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {

    if (value == null) {
      return true;
    } else if (value instanceof String) {
      String s = (String) value;
      if (!StringUtils.hasText(s)) {
        return allowEmpty;
      }
    } else if (value instanceof CharSequence) {
      CharSequence c = (CharSequence) value;
      if (!StringUtils.hasText(c)) {
        return allowEmpty;
      }
    }

    return false;
  }

}
