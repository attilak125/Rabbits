package com.cala.rabbits.models.training;

public enum PassType {
  Beginner(4), Intermediate(8),Advanced(12),Pro(100);

  private final int amount;

  PassType(int amount) {
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }
}
