package com.xeline.core.util.basis;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;

import org.springframework.core.ResolvableType;

/**
 * @author xenron
 */
public class ResolvableTypeUtils {

  public static <T> Class<T> resolveClass(Class<?> sourceClass, String typeVariableName) {
    ResolvableType type = ResolvableType.forClass(sourceClass);
    return resolveClass(type, typeVariableName);
  }

  @SuppressWarnings("unchecked")
  public static <T> Class<T> resolveClass(ResolvableType type, String typeVariableName) {
    if (type != ResolvableType.NONE) {
      if (type.getType() instanceof ParameterizedType) {
        TypeVariable<?>[] variables = type.resolve().getTypeParameters();
        for (int i = 0; i < variables.length; i++) {
          ResolvableType generic = type.getGeneric(i);
          if (generic != ResolvableType.NONE && variables[i].getName().equals(typeVariableName)) {
            return (Class<T>) generic.getType();
          }
        }
      }
      return resolveClass(type.getSuperType(), typeVariableName);
    }
    return null;
  }

}
