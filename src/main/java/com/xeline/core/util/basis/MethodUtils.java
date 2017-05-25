package com.xeline.core.util.basis;

import java.lang.reflect.Method;
import java.util.Optional;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

/**
 * @author xenron.
 */
public class MethodUtils {

  public static Method findMethod(Class<?> clazz, String name) {
    return ReflectionUtils.findMethod(clazz, name);
  }

  public static Method findMethod(Class<?> clazz, String name, Class<?>... paramTypes) {
    return ReflectionUtils.findMethod(clazz, name, paramTypes);
  }

  public static Optional<Method> optFindMethod(Class<?> clazz, String name) {
    return Optional.ofNullable(findMethod(clazz, name));
  }

  public static Optional<Method> optFindMethod(Class<?> clazz, String name, Class<?>... paramTypes) {
    return Optional.ofNullable(findMethod(clazz, name, paramTypes));
  }

  public static Object invoke(Method method, Object target) {
    return invoke(Object.class, method, target);
  }

  public static Object invoke(Method method, Object target, Object... args) {
    return invoke(Object.class, method, target, args);
  }

  public static Object invoke(String name, Object target) {
    return invoke(Object.class, name, target);
  }

  public static <T> T invoke(Class<T> returnType, String name, Object target) {
    Assert.notNull(target);
    Method method = findMethod(target.getClass(), name);
    return invoke(returnType, method, target);
  }

  public static <T> T invoke(Class<T> returnType, Method method, Object target, Object... args) {
    ReflectionUtils.makeAccessible(method);
    Object value = ReflectionUtils.invokeMethod(method, target, args);
    return value != null ? returnType.cast(value) : null;
  }

}
