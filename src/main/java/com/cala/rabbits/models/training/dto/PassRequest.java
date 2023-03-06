package com.cala.rabbits.models.training.dto;

public class PassRequest {
  private String email;

  public PassRequest(String email) {
    this.email = email;
  }

  public PassRequest() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
