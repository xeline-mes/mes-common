package com.xeline.core.audit.spi;

public interface AuditLogCreator<T, R> {

  public R createMessage(T target);

}
