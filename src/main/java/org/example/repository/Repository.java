package org.example.repository;

public abstract class Repository {
  protected int lastId;

  Repository() {
    lastId = 0;
  }

  public int getLastId() {
    return lastId;
  }

  public int getNewId() {
    return lastId + 1;
  }
}