package com.xeline.core.validation.version;

import static org.springframework.util.ClassUtils.getUserClass;
import static org.springframework.util.ReflectionUtils.doWithFields;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Version;

import org.springframework.context.MessageSource;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.FieldFilter;
import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;
import com.xeline.core.annotation.VersionCheck;
import com.xeline.core.exception.DuplicateVersionCheckKeyException;
import com.xeline.core.exception.VersionCheckKeyRequiredException;
import com.xeline.core.util.basis.FieldUtils;
import com.xeline.core.util.basis.ListUtils;
import com.xeline.core.validation.version.spi.VersionCheckKeyGenerator;
import com.xeline.core.validation.version.spi.VersionCheckResult;
import com.xeline.core.validation.version.spi.VersionChecker;

/**
 * @author xenron
 */
public abstract class AbstractVersionChecker<T> implements VersionChecker {

  @Inject
  private MessageSource messageSource;

  @Inject
  private VersionCheckKeyGenerator keyGenerator;

  VersionCheck versionCheck = null;

  VersionCheckKeyRequiredException ex1 = null;

  DuplicateVersionCheckKeyException ex2 = null;

  @Override
  public VersionCheckResult validate(Object o1, Object o2) {
    return equalsVersionWithAnnotation(o1, o2);
  }

  @Override
  public VersionCheckResult validateList(String[] selectedArray, List<?> list, List<?> outer) {
    return equalsVersionWithAnnotation(selectedArray, list, outer);
  }

  protected VersionCheckResult equalsVersionWithAnnotation(Object o1, Object o2) {

    List<String> names = new ArrayList<>();
    validate(o1, o2, names);

    return createResult(names, null);
  }

  protected VersionCheckResult equalsVersionWithAnnotation(String[] selectedArray, List<?> list, List<?> outer) {

    Assert.notNull(selectedArray, "selectedArray must be not null.");
    Assert.notNull(list, "list must be not null.");
    Assert.notNull(outer, "outer must be not null.");

    Map<String, Object> versionKeyMapList = getVersionKeyMap(list);
    Map<String, Object> versionKeyMapOuter = getVersionKeyMap(outer);

    List<String> names = new ArrayList<>();
    List<String> errorKeys = new ArrayList<>();

    for (String key : selectedArray) {

      if (!versionKeyMapList.containsKey(key)) {
        add(errorKeys, key);
      } else {
        Object o1 = versionKeyMapList.get(key);
        if (!versionKeyMapOuter.containsKey(key)) {
          add(errorKeys, key);
        } else {
          Object o2 = versionKeyMapOuter.get(key);

          boolean error = validate(o1, o2, names);
          if (error) {
            add(errorKeys, key);
          }
        }

      }

    }

    return createResult(names, errorKeys);
  }

  protected boolean validate(Object o1, Object o2, final List<String> names) {

    Assert.notNull(o1, "o1 must be not null.");
    Assert.notNull(o2, "o2 must be not null.");

    Map<String, T> versionMap1 = getVersionMap(o1);
    Map<String, T> versionMap2 = getVersionMap(o2);

    boolean error = false;
    for (Map.Entry<String, T> e : versionMap1.entrySet()) {

      String key = e.getKey();
      if (versionMap2.containsKey(key)) {

        T version1 = e.getValue();
        T version2 = versionMap2.get(key);

        if (version1 == null && version2 == null) {
          // success
        } else if (version1 == null && version2 != null) {
          add(names, key);
          error = true;
        } else if (version1 != null && version2 == null) {

        } else {
          if (!validateVersion(version1, version2)) {
            add(names, key);
            error = true;
          }
        }
      }
    }
    return error;
  }

  protected abstract boolean validateVersion(T version1, T version2);

  protected Map<String, T> getVersionMap(Object o) {

    Class<?> clazz = getUserClass(o.getClass());

    Map<String, T> versionMap = new HashMap<>();

    FieldCallback fc = createFieldCallback(clazz, o, versionMap);
    FieldFilter ff = createFieldFilter();

    doWithFields(clazz, fc, ff);

    return versionMap;
  }

  protected Map<String, Object> getVersionKeyMap(List<?> list) {

    Object o = list.get(0);
    Class<?> clazz = getUserClass(o.getClass());

    List<String> versionKeyFields = keyGenerator.getVersionFields(o);

    if (ListUtils.isEmpty(versionKeyFields)) {
      throw new VersionCheckKeyRequiredException(messageSource, clazz.getName());
    }

    Map<String, Object> versionKeyMap = Maps.newHashMap();

    for (Object dto : list) {

      String key = keyGenerator.generateKey(dto, versionKeyFields);
      if (versionKeyMap.containsKey(key)) {
        throw new DuplicateVersionCheckKeyException(messageSource, key, clazz.getName());
      }
      versionKeyMap.put(key, dto);
    }

    return versionKeyMap;
  }

  protected VersionCheckResult createResult(List<String> names, List<String> errorKeys) {
    if (0 < names.size()) {
      return new DefaultVersionCheckResult(StringUtils.toStringArray(names), StringUtils.toStringArray(errorKeys));
    } else {
      return new DefaultVersionCheckResult();
    }
  }

  protected void add(List<String> names, String key) {
    if (!names.contains(key)) {
      names.add(key);
    }
  }

  protected abstract Class<?> getAssignableClass();

  protected FieldCallback createFieldCallback(final Class<?> clazz, final Object target, final Map<String, T> versionMap) {
    return new FieldCallback() {

      @SuppressWarnings("unchecked")
      @Override
      public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {

        Object v = FieldUtils.getField(field, target);
        if (v != null && !ClassUtils.isAssignable(v.getClass(), getAssignableClass())) {
          throw new IllegalArgumentException(String.format("@VersionCheck annotated field must be [%s].", getAssignableClass().getName()));
        }
        T value = (T) v;
        String key = getKey(field);
        versionMap.put(key, value);
      }

      protected String getKey(Field field) {
        VersionCheck ann = field.getAnnotation(VersionCheck.class);
        if (ann == null) {
          return field.getName();
        } else {
          String key = ann.value();
          if (StringUtils.hasText(key)) {
            return key;
          } else {
            return field.getName();
          }
        }
      }

    };
  }

  protected FieldFilter createFieldFilter() {
    return f -> f.getAnnotation(VersionCheck.class) != null || f.getAnnotation(Version.class) != null;
  }

  protected MessageSource getMessageSource() {
    return this.messageSource;
  }

  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  public VersionCheckKeyGenerator getKeyGenerator() {
    return keyGenerator;
  }

  public void setKeyGenerator(VersionCheckKeyGenerator keyGenerator) {
    this.keyGenerator = keyGenerator;
  }

}
