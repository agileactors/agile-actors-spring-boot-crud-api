package com.agileactors.crud.autoconfiguration;

import com.agileactors.crud.properties.SpringRestProperties;
import com.agileactors.crud.service.MappingService;
import com.agileactors.crud.service.MappingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.convert.ConversionService;

@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class CrudAutoConfiguration {

  @Autowired
  private SpringRestProperties springRestProperties;
  @Autowired
  private ConversionService conversionService;

  @Bean
  public MappingService mappingService() {
    return new MappingServiceImpl(springRestProperties, conversionService);
  }

}
