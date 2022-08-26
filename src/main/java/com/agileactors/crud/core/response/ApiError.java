package com.agileactors.crud.core.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {

  private LocalDateTime timestamp;
  private String message;

}