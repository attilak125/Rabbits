package com.cala.rabbits.models.training.dto;

public class RemoveFromSessionRequest {
  private String email;

  public RemoveFromSessionRequest(String email) {
    this.email = email;
  }

  public RemoveFromSessionRequest() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
