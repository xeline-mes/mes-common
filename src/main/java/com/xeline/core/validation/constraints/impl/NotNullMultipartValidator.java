package com.xeline.core.validation.constraints.impl;

import java.util.Arrays;
import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import com.xeline.core.annotation.validation.constraints.NotNullMultipart;

/**
 * @author xenron
 */
public class NotNullMultipartValidator implements ConstraintValidator<NotNullMultipart, Object> {

  private boolean allowArrayOrCollectionEmpty;

  @Override
  public void initialize(NotNullMultipart constraintAnnotation) {
    allowArrayOrCollectionEmpty = constraintAnnotation.allowArrayOrCollectionEmpty();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {

    if (value == null) {
      return false;
    } else {
      if (isMultipartFile(value)) {
        return validateMultipartFile(value);
      } else {
        return true;
      }
    }
  }

  @SuppressWarnings("unchecked")
  protected boolean validateMultipartFile(Object value) {

    if (value instanceof MultipartFile) {
      MultipartFile file = (MultipartFile) value;
      return !file.isEmpty();
    } else if (value instanceof MultipartFile[]) {
      MultipartFile[] files = (MultipartFile[]) value;
      boolean anyMatch = Arrays.stream(files).anyMatch(f -> !f.isEmpty());
      if (anyMatch) {
        return true;
      }
      return allowArrayOrCollectionEmpty;
    } else {
      Collection<MultipartFile> collection = (Collection<MultipartFile>) value;
      boolean anyMatch = collection.stream().anyMatch(f -> !f.isEmpty());
      if (anyMatch) {
        return true;
      }
      return allowArrayOrCollectionEmpty;
    }

  }

  protected boolean isMultipartFile(Object value) {

    if (value instanceof MultipartFile) {
      return true;
    } else if (value instanceof MultipartFile[]) {
      return true;
    } else if (value instanceof Collection) {
      Collection<?> collection = (Collection<?>) value;
      if (collection.isEmpty()) {
        // unresolved type.
        return !allowArrayOrCollectionEmpty;
      }
      Object o = collection.iterator().next();
      Class<?> elementClass = o.getClass();
      return MultipartFile.class.isAssignableFrom(elementClass);

    } else {
      return false;
    }
  }

}
