package com.agileactors.crud.core.response;

import com.agileactors.crud.exception.DomainResourceNotFoundException;
import java.time.LocalDateTime;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler({DomainResourceNotFoundException.class})
  public ResponseEntity<ApiError> handleDomainResourceNotFoundException(
      DomainResourceNotFoundException ex, WebRequest request) {

    ApiError apiError = new ApiError(LocalDateTime.now(),
        String.format("Resource [%s] not found", ex.getResourceId().toString()));

    return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

}