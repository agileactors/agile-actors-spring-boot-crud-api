package com.agileactors.crud.service;

/**
 * All possible mapping types.
 *
 * @author Alexis Panousis
 */
public enum MappingType {
  API_RESPONSE_GET("api.response.get"),
  API_RESPONSE_CREATE("api.response.create"),
  API_RESPONSE_UPDATE("api.response.update"),
  DOMAIN("domain");

  private final String value;

  MappingType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
