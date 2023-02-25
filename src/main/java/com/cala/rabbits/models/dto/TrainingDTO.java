package com.cala.rabbits.models.dto;

import com.cala.rabbits.models.TrainingType;
import java.time.LocalDate;

public class TrainingDTO {

  private String type;
  private LocalDate creationDate;
  private String exercises;

  public TrainingDTO(TrainingType type, LocalDate creationDate, String exercises) {
    this.type = type.toString();
    this.creationDate = creationDate;
    this.exercises = exercises;
  }

  public TrainingType getType() {
    return TrainingType.valueOf(type);
  }

  public void setType(TrainingType type) {
    this.type = type.toString();
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public String getExercises() {
    return exercises;
  }

  public void setExercises(String exercises) {
    this.exercises = exercises;
  }
}
