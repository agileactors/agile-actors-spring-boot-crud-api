package com.agileactors.crud.exception;

public class DomainResourceNotFoundException extends ApplicationException {

  private final Object resourceId;

  public DomainResourceNotFoundException(String message, Object resourceId) {
    super(message);
    this.resourceId = resourceId;
  }

  public Object getResourceId() {
    return resourceId;
  }
}
