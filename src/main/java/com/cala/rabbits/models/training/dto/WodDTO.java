package com.cala.rabbits.models.training.dto;

public class WodDTO {
  private String exercises;
  private int rounds;

  public WodDTO(String exercises, int rounds) {
    this.exercises = exercises;
    this.rounds = rounds;
  }

  public WodDTO() {
  }

  public String getExercises() {
    return exercises;
  }

  public void setExercises(String exercises) {
    this.exercises = exercises;
  }

  public int getRounds() {
    return rounds;
  }

  public void setRounds(int rounds) {
    this.rounds = rounds;
  }
}
