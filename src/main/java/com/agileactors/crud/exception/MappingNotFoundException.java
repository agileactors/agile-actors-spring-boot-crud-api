package com.agileactors.crud.exception;

/**
 * Thrown when mapping is not defined at classpat:spring-rest-properties.yml file
 *
 * @author Alexis Panousis
 */
public class MappingNotFoundException extends ApplicationException {

  public MappingNotFoundException(String message) {
    super(message);
  }
}
