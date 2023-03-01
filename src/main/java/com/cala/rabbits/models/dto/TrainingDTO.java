package com.cala.rabbits.models.dto;

import com.cala.rabbits.models.TrainingType;
import java.time.LocalDate;

public class TrainingDTO {

  private String type;
  private LocalDate doDate;
  private String exercises;

  public TrainingDTO(TrainingType type, LocalDate doDate, String exercises) {
    this.type = type.toString();
    this.doDate = doDate;
    this.exercises = exercises;
  }

  public TrainingType getType() {
    return TrainingType.valueOf(type);
  }

  public void setType(TrainingType type) {
    this.type = type.toString();
  }

  public LocalDate getDoDate() {
    return doDate;
  }

  public void setDoDate(LocalDate doDate) {
    this.doDate = doDate;
  }

  public String getExercises() {
    return exercises;
  }

  public void setExercises(String exercises) {
    this.exercises = exercises;
  }
}
