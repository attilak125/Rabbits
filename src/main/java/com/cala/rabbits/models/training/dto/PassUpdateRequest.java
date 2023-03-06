package com.cala.rabbits.models.training.dto;

import com.cala.rabbits.models.training.PassType;
import java.time.LocalDate;

public class PassUpdateRequest extends PassCreationRequest{

  public PassUpdateRequest(String email, PassType passType,
      LocalDate creationDate) {
    super(email, passType, creationDate);
  }
}
