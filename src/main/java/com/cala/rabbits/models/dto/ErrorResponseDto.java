package com.cala.rabbits.models.dto;

public class ErrorResponseDto {
  public String message;

  public ErrorResponseDto(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
