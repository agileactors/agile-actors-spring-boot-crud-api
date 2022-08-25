package com.agileactors.crud.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractGetResourceResponseDto<I extends Serializable> {
  private final I id;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  // Used generated methods here since @Getter was not working right with transforming
  // the fields to the JSON when used as an api response
  public I getId() {
    return id;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
