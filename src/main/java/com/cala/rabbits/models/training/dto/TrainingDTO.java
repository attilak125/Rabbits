package com.cala.rabbits.models.training.dto;

import com.cala.rabbits.models.training.TrainingType;
import com.cala.rabbits.models.training.Wod;
import java.util.List;

public class TrainingDTO {

  private String type;
  private List<WodDTO> exercises;

  public TrainingDTO(TrainingType type, List<WodDTO> exercises) {
    this.type = type.toString();
    this.exercises = exercises;
  }

  public TrainingType getType() {
    return TrainingType.valueOf(type);
  }

  public void setType(TrainingType type) {
    this.type = type.toString();
  }

  public List<WodDTO> getExercises() {
    return exercises;
  }

  public void setExercises(List<WodDTO> exercises) {
    this.exercises = exercises;
  }

}
