package com.cala.rabbits.models.user;

import com.cala.rabbits.models.training.PassType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "pass")
public class Pass {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  int amountLeft;
  LocalDate creationDate;
  LocalDate expiresOn;
  boolean expired;
  @OneToOne(mappedBy = "pass")
  private User user;

  public Pass(Long id, PassType passType, LocalDate creationDate, User user) {
    this.id = id;
    this.amountLeft = passType.getAmount();
    this.creationDate = creationDate;
    this.expiresOn = creationDate.plusDays(30);
    this.expired = false;
    this.user = user;
  }

  public Pass(PassType passType, LocalDate creationDate, User user) {
    this.amountLeft = passType.getAmount();
    this.creationDate = creationDate;
    this.expiresOn = creationDate.plusDays(30);
    this.expired = false;
    this.user = user;
  }

  public Pass() {
  }

  public Long getId() {
    return id;
  }

  public int getAmountLeft() {
    return amountLeft;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public LocalDate getExpiresOn() {
    return expiresOn;
  }

  public boolean isExpired() {
    return expired;
  }

  public User getUser() {
    return user;
  }

  public void setAmountLeft(int amountLeft) {
    this.amountLeft = amountLeft;
    if (amountLeft <= 0){
      this.expired=true;
    }
  }

  public void setExpired(boolean expired) {
    this.expired = expired;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
    this.expiresOn=creationDate.plusDays(30);
  }
}
