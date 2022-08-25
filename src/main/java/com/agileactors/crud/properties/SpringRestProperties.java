package com.agileactors.crud.properties;

import com.agileactors.crud.properties.support.YamlPropertySourceFactory;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "spring.rest")
@PropertySource(value = "classpath:spring-rest-properties.yml",
    factory = YamlPropertySourceFactory.class)
@ConditionalOnResource(resources = "classpath:spring-rest-properties.yml")
@Data
public class SpringRestProperties {

  private final Map<String, String> mappings;

}
