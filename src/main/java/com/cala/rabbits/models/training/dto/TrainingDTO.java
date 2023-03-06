package com.cala.rabbits.models.training.dto;

import com.cala.rabbits.models.training.TrainingType;
import com.cala.rabbits.models.training.Wod;
import java.util.List;

public class TrainingDTO {

  private String type;
  private List<Wod> exercises;

  public TrainingDTO(String type, List<Wod> exercises) {
    this.type = type;
    this.exercises = exercises;
  }

  public TrainingType getType() {
    return TrainingType.valueOf(type);
  }

  public void setType(TrainingType type) {
    this.type = type.toString();
  }

  public List<Wod> getExercises() {
    return exercises;
  }

  public void setExercises(List<Wod> exercises) {
    this.exercises = exercises;
  }

}
