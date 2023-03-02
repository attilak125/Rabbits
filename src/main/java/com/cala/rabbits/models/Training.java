package com.cala.rabbits.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

@Entity
@Table(name = "trainings")
public class Training {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String type;
  private LocalDate doDate;
  private String exercises;
  private String day;
  private int week;

  public Training(long id, TrainingType type, LocalDate doDate, String exercises, String day, int week) {
    this.id = id;
    this.type = type.toString();
    this.doDate = doDate;
    this.exercises = exercises;
    this.day = day;
    this.week = week;
  }

  public Training(TrainingType type, LocalDate doDate, String exercises) {
    this.type = type.toString();
    this.doDate = doDate;
    this.exercises = exercises;
    this.day = doDate.getDayOfWeek().name();
    this.week = doDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
  }

  public Training() {
  }

  public long getId() {
    return id;
  }

  public TrainingType getType() {
    return TrainingType.valueOf(type);
  }

  public void setType(TrainingType type) {
    this.type = type.toString();
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getExercises() {
    return exercises;
  }

  public void setExercises(String exercises) {
    this.exercises = exercises;
  }

  public LocalDate getDoDate() {
    return doDate;
  }

  public void setDoDate(LocalDate doDate) {
    this.doDate = doDate;
  }

  public String getDay() {
    return day;
  }

  public void setDay(LocalDate date) {
    this.day = date.getDayOfWeek().name();
  }
  public int getWeek() {
    return week;
  }
}
