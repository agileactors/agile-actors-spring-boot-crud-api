package com.agileactors.crud.service;

import com.agileactors.crud.exception.MappingNotFoundException;
import com.agileactors.crud.properties.SpringRestProperties;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;

/**
 * Default implementation of the {@link MappingService} interface.
 */
@RequiredArgsConstructor
public final class MappingServiceImpl implements MappingService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final SpringRestProperties springRestProperties;

  private final ConversionService conversionService;

  /*
   * (non-Javadoc)
   * @see MappingService#convert(Object source, MappingType targetMappingType)
   */
  public Object convert(Object source, MappingType targetMappingType)
      throws MappingNotFoundException {
    return conversionService.convert(source,
        getTargetMappingType(source.getClass(), targetMappingType));
  }

  /**
   * Returns the mapping type defined at {@link SpringRestProperties#mappings}.
   *
   * @param source            the source object
   * @param targetMappingType the target object's type
   * @return the target mapping class
   * @throws MappingNotFoundException when mapping doesn't exist at the relevant file
   */
  private Class<?> getTargetMappingType(Class<?> source, MappingType targetMappingType)
      throws MappingNotFoundException {

    final String mappingKey = source.getName() + "." + targetMappingType.getValue();

    try {
      return Class.forName(springRestProperties.getMappings().get(mappingKey));
    } catch (ClassNotFoundException ex) {
      logger.error(String.format("Mapping %s not found", mappingKey));
      throw new MappingNotFoundException(String.format("Mapping %s not found", mappingKey));
    }
  }
}
