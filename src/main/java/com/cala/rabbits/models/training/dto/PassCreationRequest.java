package com.cala.rabbits.models.training.dto;

import com.cala.rabbits.models.training.PassType;
import java.time.LocalDate;

public class PassCreationRequest {
  private String email;
  private PassType passType;
  private LocalDate creationDate;

  public PassCreationRequest(String email, PassType passType, LocalDate creationDate) {
    this.email = email;
    this.passType = passType;
    this.creationDate = creationDate;
  }

  public PassCreationRequest() {
  }

  public String getEmail() {
    return email;
  }

  public PassType getPassType() {
    return passType;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassType(PassType passType) {
    this.passType = passType;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }
}
