package com.xeline.core.util.basis;

import java.lang.reflect.Field;
import java.util.Optional;

import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

/**
 * @author xenron
 */
public class FieldUtils {

  public static Field findField(Class<?> clazz, String fieldName) {
    return ReflectionUtils.findField(clazz, fieldName);
  }

  public static Optional<Field> optFindField(Class<?> clazz, String fieldName) {
    return Optional.ofNullable(findField(clazz, fieldName));
  }

  public static Object getField(String fieldName, Object target) {

    Class<?> clazz = ClassUtils.getUserClass(target);
    Field field = ReflectionUtils.findField(clazz, fieldName);

    return getField(field, target);
  }

  public static <T> T getField(String fieldName, Object target, Class<T> returnType) {

    Class<?> clazz = ClassUtils.getUserClass(target);
    Field field = ReflectionUtils.findField(clazz, fieldName);

    return getField(field, target, returnType);
  }

  public static Object getField(Field field, Object target) {

    ReflectionUtils.makeAccessible(field);

    Object value = ReflectionUtils.getField(field, target);
    return value;
  }

  public static <T> T getField(Field field, Object target, Class<T> returnType) {

    Object value = getField(field, target);
    return value != null ? returnType.cast(value) : null;
  }

  public static Optional<Object> optField(Field field, Object target) {
    return Optional.ofNullable(getField(field, target));
  }

  public static <T> Optional<T> optField(Field field, Object target, Class<T> returnType) {
    return Optional.ofNullable(getField(field, target, returnType));
  }

  public static Optional<Object> optField(String fieldName, Object target) {
    return Optional.ofNullable(getField(fieldName, target));
  }

  public static <T> Optional<T> optField(String fieldName, Object target, Class<T> returnType) {
    return Optional.ofNullable(getField(fieldName, target, returnType));
  }

  public static void setField(Field field, Object target, Object value) {
    ReflectionUtils.makeAccessible(field);
    ReflectionUtils.setField(field, target, value);
  }

  public static void setField(String fieldName, Object target, Object value) {

    Class<?> clazz = ClassUtils.getUserClass(target);

    Field field = findField(clazz, fieldName);
    setField(field, target, value);
  }

}
