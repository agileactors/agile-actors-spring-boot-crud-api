package com.agileactors.crud.autoconfiguration;

import com.agileactors.crud.properties.SpringRestProperties;
import com.agileactors.crud.service.MappingService;
import com.agileactors.crud.service.MappingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.convert.ConversionService;

@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
@EnableConfigurationProperties({SpringRestProperties.class})
public class CrudAutoConfiguration {

  @Bean
  public MappingService mappingService(SpringRestProperties springRestProperties,
                                       ConversionService conversionService) {
    return new MappingServiceImpl(springRestProperties, conversionService);
  }

}
