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

  public Training() {
  }
  public Training(TrainingType type) {
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public TrainingType getType() {
    return type;
  }

  public void setType(TrainingType type) {
    this.type = type;
  }

  public List<Wod> getWods() {
    return wods;
  }

  public void setWods(List<Wod> wods) {
    this.wods = wods;
  }

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }
}
