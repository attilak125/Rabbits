package com.cala.rabbits.models.training.dto;

import java.time.LocalDate;

public class SessionsDTO {
  private LocalDate doDate;
  private String day;
  private int week;

  public SessionsDTO(LocalDate doDate, String day, int week) {
    this.doDate = doDate;
    this.day = day;
    this.week = week;
  }

  public SessionsDTO() {
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

  public void setDay(String day) {
    this.day = day;
  }

  public int getWeek() {
    return week;
  }

  public void setWeek(int week) {
    this.week = week;
  }
}
