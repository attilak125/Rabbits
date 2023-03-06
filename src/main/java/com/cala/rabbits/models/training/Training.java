package com.cala.rabbits.models.training;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "trainings")
public class Training {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Enumerated(EnumType.STRING)
  private TrainingType type;
  @OneToMany(mappedBy = "training")
  private List<Wod> wods;
  @OneToOne(mappedBy = "training")
  private Session session;

//  @ManyToMany
//  @JoinTable(
//      name = "user_training",
//      joinColumns = @JoinColumn(name = "training_id"),
//      inverseJoinColumns = @JoinColumn(name = "user_id"))
//  private List<User> participants = new ArrayList<>();

//  public Training(TrainingType type, String exercises) {
//    this.type = type.toString();
//    this.doDate = doDate;
//    this.exercises = exercises;
//    this.day = doDate.getDayOfWeek().name();
//    this.week = doDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
//  }

  public Training() {
  }
  public Training(TrainingType type, List<Wod> wods) {
    this.type = type;
    this.wods = wods;
  }
  //  public long getId() {
//    return id;
//  }
//
//  public TrainingType getType() {
//    return TrainingType.valueOf(type);
//  }
//
//  public void setType(TrainingType type) {
//    this.type = type.toString();
//  }
//
//  public void setType(String type) {
//    this.type = type;
//  }
//
//  public String getExercises() {
//    return exercises;
//  }
//
//  public void setExercises(String exercises) {
//    this.exercises = exercises;
//  }
//
//  public LocalDate getDoDate() {
//    return doDate;
//  }
//
//  public void setDoDate(LocalDate doDate) {
//    this.doDate = doDate;
//  }
//
//  public String getDay() {
//    return day;
//  }
//
//  public void setDay(LocalDate date) {
//    this.day = date.getDayOfWeek().name();
//  }
//  public int getWeek() {
//    return week;
//  }
//
//  public List<User> getParticipants() {
//    return participants;
//  }
//
//  public void addParticipants(User user) {
//    this.participants.add(user);
//  }
}
