package com.cala.rabbits.models.dto;

import com.cala.rabbits.models.PassType;
import java.time.LocalDate;

public class PassCreationRequest {
  private final String email;
  private final PassType passType;
  private final LocalDate creationDate;

  public PassCreationRequest(String email, PassType passType, LocalDate creationDate) {
    this.email = email;
    this.passType = passType;
    this.creationDate = creationDate;
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
}
