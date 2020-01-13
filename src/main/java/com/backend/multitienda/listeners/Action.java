package com.backend.multitienda.listeners;

import org.hibernate.dialect.function.DB2SubstringFunction;

public enum Action {
  INSERTED ("INSERTED"),
  UPDATED("UPDATED"),
  DELETED("DELETED");

  private final String name;

  Action(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
}
