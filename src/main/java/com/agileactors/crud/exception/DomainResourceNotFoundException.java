package com.agileactors.crud.exception;

/**
 * Thrown when trying to retrieve a missing domain object.
 *
 * @author Alexis Panousis
 */
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
