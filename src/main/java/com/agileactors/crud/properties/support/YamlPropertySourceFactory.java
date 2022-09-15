package com.agileactors.crud.properties.support;

import java.util.Properties;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

/**
 * A factory used to read yaml property files.
 *
 * @author Alexis Panousis
 */
public final class YamlPropertySourceFactory implements PropertySourceFactory {

  /*
   * (non-Javadoc)
   * @see PropertySourceFactory#createPropertySource(java.lang.String, org.springframework.core.io.support.EncodedResource)
   */
  @Override
  public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) {
    YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
    factory.setResources(encodedResource.getResource());

    Properties properties = factory.getObject();

    return new PropertiesPropertySource(encodedResource.getResource().getFilename(), properties);
  }
}