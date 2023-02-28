package com.cala.rabbits.models.dto;

public class EmailVerificationRequest {
  private String email;

  public EmailVerificationRequest(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
