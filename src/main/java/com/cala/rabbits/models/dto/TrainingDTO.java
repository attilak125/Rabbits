package com.cala.rabbits.models.dto;

import java.time.LocalDate;

public class TrainingDTO {

  private String type;
  private LocalDate creationDate;
  private String exercises;

  public TrainingDTO(String type, LocalDate creationDate, String exercises) {
    this.type = type;
    this.creationDate = creationDate;
    this.exercises = exercises;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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
