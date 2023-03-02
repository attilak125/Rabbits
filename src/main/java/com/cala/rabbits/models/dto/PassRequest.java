package com.cala.rabbits.models.dto;

public class PassRequest {
  private final String email;

  public PassRequest(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

}
