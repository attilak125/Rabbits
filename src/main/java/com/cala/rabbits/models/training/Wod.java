package com.cala.rabbits.models.training;

import com.cala.rabbits.models.training.Training;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Wod {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String exercises;
  private int rounds;

  @ManyToOne
  @JoinColumn(name = "training_id")
  private Training training;

  public Wod(String exercises, int rounds, Training training) {
    this.exercises = exercises;
    this.rounds = rounds;
    this.training = training;
  }

  public Wod(String exercises, int rounds) {
    this.exercises = exercises;
    this.rounds = rounds;
  }

  public Wod() {
  }

  public Long getId() {
    return id;
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

  public Training getTraining() {
    return training;
  }

  public void setTraining(Training training) {
    this.training = training;
  }
}
