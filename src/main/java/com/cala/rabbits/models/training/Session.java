package com.cala.rabbits.models.training;

import com.cala.rabbits.models.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sessions")
public class Session {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private LocalDate doDate;
  private String day;
  private int week;
  @OneToOne
  @JoinColumn(name = "training_id")
  private Training training;
  @ManyToMany
  @JoinTable(
      name = "user_session",
      joinColumns = @JoinColumn(name = "session_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> participants = new ArrayList<>();

  public Session(Long id, LocalDate doDate) {
    this.id = id;
    this.doDate = doDate;
  }

  public Session(LocalDate doDate) {
    this.doDate = doDate;
    this.day=doDate.getDayOfWeek().name();
    this.week=doDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
  }

  public Session() {
  }

  public Long getId() {
    return id;
  }

  public LocalDate getDoDate() {
    return doDate;
  }

  public String getDay() {
    return day;
  }

  public int getWeek() {
    return week;
  }

  public Training getTraining() {
    return training;
  }

  public List<User> getParticipants() {
    return participants;
  }

  public void setDoDate(LocalDate doDate) {
    this.doDate = doDate;
    this.day=doDate.getDayOfWeek().name();
    this.week=doDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
  }

  public void setTraining(Training training) {
    this.training = training;
  }

  public void addParticipant(User participant) {
    this.participants.add(participant);
  }

  public void removeParticipantFromSession(User participant){
    this.participants.remove(participant);
  }
}
