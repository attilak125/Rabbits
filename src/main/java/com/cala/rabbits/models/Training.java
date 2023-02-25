package com.cala.rabbits.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "trainings")
public class Training {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String type;
  private LocalDate creationDate;
  private String exercises;

  public Training(TrainingType type, LocalDate creationDate, String exercises) {
    this.type = type.toString();
    this.creationDate = creationDate;
    this.exercises = exercises;
  }

  public Training() {
  }

  public long getId() {
    return id;
  }

  public TrainingType getType() {
    return TrainingType.valueOf(type);
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public String getExercises() {
    return exercises;
  }

  public void setType(TrainingType type) {
    this.type = type.toString();
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public void setExercises(String exercises) {
    this.exercises = exercises;
  }
}
