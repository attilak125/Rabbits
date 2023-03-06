package com.cala.rabbits.models.training.dto;

public class JoinSessionRequest {
  private String email;

  public JoinSessionRequest(String email) {
    this.email = email;
  }

  public JoinSessionRequest() {
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }
}
