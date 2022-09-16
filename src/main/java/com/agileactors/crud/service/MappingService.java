package com.agileactors.crud.service;

import com.agileactors.crud.exception.MappingNotFoundException;

/**
 * The service used for interpreting the mapping defined at
 * {@link com.agileactors.crud.properties.SpringRestProperties}.
 * Since the service uses the {@link org.springframework.core.convert.ConversionService} to perform
 * the conversion it is mandatory that the relevant converted is implemented.
 *
 * @author Alexis Panousis
 */
public interface MappingService {

  /**
   * Converts the given object to the target mapping type.
   *
   * @param source            the source object
   * @param targetMappingType the target object's type
   * @return a new instance of the target object's type
   * @throws MappingNotFoundException when mapping doesn't exist at the relevant file
   */
  Object convert(Object source, MappingType targetMappingType)
      throws MappingNotFoundException;
}
