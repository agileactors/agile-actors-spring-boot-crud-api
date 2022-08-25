package com.agileactors.crud.service;

import com.agileactors.crud.exception.MappingNotFoundException;

public interface MappingService {
  Class<?> getResponseMappingType(Class<?> type, String mappingType)
      throws MappingNotFoundException;
}
