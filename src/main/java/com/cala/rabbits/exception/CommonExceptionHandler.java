package com.cala.rabbits.exception;

import com.cala.rabbits.models.dto.ErrorResponseDto;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {
  private final Environment environment;

  public CommonExceptionHandler(Environment environment) {
    this.environment = environment;
  }

  @ExceptionHandler(value = InvalidIdException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponseDto handleTrainingNotFoundException(InvalidIdException invalidIdException){
    return new ErrorResponseDto(environment.getProperty("config.errors.invalid_id"));
  }

  @ExceptionHandler(value = RequestBodyMissingException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleRequestBodyMissingException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.request_body_missing"));
  }
}
