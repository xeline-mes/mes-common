package com.xeline.core.validation.version;

import static org.springframework.util.ClassUtils.getUserClass;
import static org.springframework.util.ReflectionUtils.doWithFields;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.FieldFilter;

import com.xeline.core.annotation.VersionCheckKey;
import com.xeline.core.util.basis.ListUtils;
import com.xeline.core.validation.version.spi.VersionCheckKeyGenerator;

/**
 * @author xenron
 */
public class DefaultVersionCheckKeyGenerator implements VersionCheckKeyGenerator {

  VersionCheckKey versionCheckKey = null;

  @Override
  public List<String> getVersionFields(Object dto) {
    if (dto == null) {
      return null;
    }

    Class<?> clazz = getUserClass(dto.getClass());

    List<String> versionKeyFields = new ArrayList<>();

    FieldCallback fc = createVersionKeyFieldCallback(clazz, dto, versionKeyFields);
    doWithFields(clazz, fc, VERSION_CHECK_KEY_FIELDS);
    return versionKeyFields;
  }

  @Override
  public String generateKey(Object dto) {

    List<String> fieldNames = getVersionFields(dto);
    if (ListUtils.isEmpty(fieldNames)) {
      return null;
    }

    return generateKey(dto, fieldNames);
  }

  @Override
  public String generateKey(Object dto, List<String> fieldNames) {

    BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(dto);

    StringBuilder sb = new StringBuilder();
    for (String fieldName : fieldNames) {
      sb.append(fieldName);
      sb.append(":");
      if (beanWrapper.isReadableProperty(fieldName)) {
        Object value = beanWrapper.getPropertyValue(fieldName);
        if (value != null) {
          sb.append(value.toString());
        }
      }
      sb.append("-");
    }

    return sb.toString();
  }

  protected FieldCallback createVersionKeyFieldCallback(final Class<?> clazz, final Object target, final List<String> versionKeyFields) {
    return f -> versionKeyFields.add(f.getName());
  }

  public static FieldFilter VERSION_CHECK_KEY_FIELDS = f -> f.getAnnotation(VersionCheckKey.class) != null;
}
