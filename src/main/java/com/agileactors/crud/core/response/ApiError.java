package com.agileactors.crud.core.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Custom error used at REST APIs
 *
 * @author Alexis Panousis
 */
@Data
@AllArgsConstructor
public class ApiError {

  private LocalDateTime timestamp;
  private String message;

}