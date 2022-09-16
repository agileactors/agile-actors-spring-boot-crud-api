package com.agileactors.crud.properties;

import com.agileactors.crud.properties.support.YamlPropertySourceFactory;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SuppressWarnings({"LineLength", "InvalidJavadocPosition"})
/**
 * The property class that holds all the library related property-values. The existence of this file
 * in the classpath is mandatory since the <code>mappings</code> property holds the mandatory mappings between the domain class
 * and the DTOs used while creating, updating and retrieving a domain class.
 *
 * <p>IMPORTANT: If no file is found under the classpath the functionality will stay inactive.
 *
 * <p>Example of src/main/resources/spring-rest-properties.yml:
 *
 * <p><pre>{@code
 * spring:
 *   rest:
 *     mappings:
 *       "com.agileactors.templateservice.domain.DomainObject.api.response.get": "com.agileactors.templateservice.dto.GetDomainObjectResponseDto"
 *       "com.agileactors.templateservice.domain.DomainObject.api.response.create": "com.agileactors.templateservice.dto.GetDomainObjectResponseDto"
 *       "com.agileactors.templateservice.domain.DomainObject.api.response.update": "com.agileactors.templateservice.dto.GetDomainObjectResponseDto"
 *       "com.agileactors.templateservice.dto.CreateDomainObjectRequestDto.domain": "com.agileactors.templateservice.domain.DomainObject"
 *       "com.agileactors.templateservice.dto.UpdateDomainObjectRequestDto.domain": "com.agileactors.templateservice.domain.DomainObject"
 * }</pre>
 *
 * <p>The pattern used for the mapping keys follows the following pattern: "fully qualified class name of source"."target action".
 *
 * <p>Lines explained:
 * <pre>{@code
 * "com.agileactors.templateservice.domain.DomainObject.api.response.create": "com.agileactors.templateservice.dto.GetDomainObjectResponseDto" : The api response of creating a com.agileactors.templateservice.domain.DomainObject is com.agileactors.templateservice.dto.GetDomainObjectResponseDto.
 * "com.agileactors.templateservice.domain.DomainObject.api.response.update": "com.agileactors.templateservice.dto.GetDomainObjectResponseDto" : The api response of updating a com.agileactors.templateservice.domain.DomainObject is com.agileactors.templateservice.dto.GetDomainObjectResponseDto.
 * "com.agileactors.templateservice.domain.DomainObject.api.response.get": "com.agileactors.templateservice.dto.GetDomainObjectResponseDto" : The api response of retrieving a com.agileactors.templateservice.domain.DomainObject is com.agileactors.templateservice.dto.GetDomainObjectResponseDto.
 * "com.agileactors.templateservice.dto.CreateDomainObjectRequestDto.domain": "com.agileactors.templateservice.domain.DomainObject" : The domain object of com.agileactors.templateservice.dto.CreateDomainObjectRequestDto is com.agileactors.templateservice.domain.DomainObject
 *
 * }</pre>
 *
 * @author Alexis Panousis
 */
@Configuration
@ConfigurationProperties(prefix = "spring.rest")
@PropertySource(value = "classpath:spring-rest-properties.yml",
    factory = YamlPropertySourceFactory.class)
@ConditionalOnResource(resources = "classpath:spring-rest-properties.yml")
@Data
public class SpringRestProperties {

  private final Map<String, String> mappings;

}
