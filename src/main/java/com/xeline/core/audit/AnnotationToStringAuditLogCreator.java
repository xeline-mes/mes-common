package com.xeline.core.audit;

import java.lang.reflect.Field;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.FieldFilter;
import org.springframework.util.StringUtils;

import com.google.common.base.StandardSystemProperty;
import com.xeline.core.annotation.AuditLog;
import com.xeline.core.annotation.AuditLog.AuditLogStyle;
import com.xeline.core.annotation.AuditLogParam;
import com.xeline.core.audit.spi.AuditLogCreator;
import com.xeline.core.exception.AuditLogParamRequiredException;
import com.xeline.core.exception.AuditLogRequiredException;
import com.xeline.core.util.basis.FieldUtils;

/**
 * @author xenron
 */
public class AnnotationToStringAuditLogCreator implements AuditLogCreator<Object, String> {

  @Inject
  private MessageSource messageSource;

  private int maxLength;

  private static final String LINE_SEP = StandardSystemProperty.FILE_SEPARATOR.value();

  @SuppressWarnings("unused")
  private AuditLog auditLog = null;

  @SuppressWarnings("unused")
  private AuditLogParam auditLogParam = null;

  /**
   * @see AuditLogCreator#createMessage(java.lang.Object)
   */
  @Override
  public String createMessage(Object target) {

    assertAuditLogAnnotaion(target);

    AuditLog auditLog = AnnotationUtils.findAnnotation(target.getClass(), AuditLog.class);

    AuditLogStyle style = auditLog.style();
    String separetor = auditLog.separator();

    StringBuilder sb = new StringBuilder();

    FieldCallback fc = createFieldCallback(target, sb, separetor, style == AuditLogStyle.MULTILINE ? true : false);

    Class<?> clazz = ClassUtils.getUserClass(target.getClass());
    ReflectionUtils.doWithFields(clazz, fc, AUDITLOGPARAM_FIELDS);

    String result = getResult(sb);
    assertAuditLogParamAnnotaion(clazz, result);

    return result;
  }

  protected String getResult(StringBuilder sb) {
    int length = sb.length();
    if (maxLength < length) {
      return sb.substring(0, maxLength);
    } else {
      return sb.toString();
    }
  }

  protected void assertAuditLogAnnotaion(Object target) {

    Class<?> clazz = ClassUtils.getUserClass(target.getClass());

    AuditLog auditLog = AnnotationUtils.findAnnotation(clazz, AuditLog.class);
    if (auditLog == null) {
      throw new AuditLogRequiredException(messageSource, clazz.getName());
    }
  }

  protected void assertAuditLogParamAnnotaion(Class<?> clazz, String result) {

    if (!StringUtils.hasText(result)) {
      throw new AuditLogParamRequiredException(messageSource, clazz.getName());
    }
  }

  protected FieldCallback createFieldCallback(final Object target, final StringBuilder sb, final String separetor, final boolean multiline) {
    return new FieldCallback() {

      @Override
      public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {

        AuditLogParam ann = field.getAnnotation(AuditLogParam.class);

        Optional<Object> value = FieldUtils.optField(field, target);

        sb.append(ann.value());
        sb.append("=");

        value.ifPresent(v -> sb.append(v.toString()));
        sb.append(separetor);

        if (multiline) {
          sb.append(LINE_SEP);
        }

      }
    };
  }

  public static FieldFilter AUDITLOGPARAM_FIELDS = new FieldFilter() {

    @Override
    public boolean matches(Field field) {
      return field.getAnnotation(AuditLogParam.class) != null;
    }
  };

  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  public void setMaxLength(int maxLength) {
    this.maxLength = maxLength;
  }

}
