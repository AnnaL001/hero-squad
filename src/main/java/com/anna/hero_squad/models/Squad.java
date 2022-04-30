package com.anna.hero_squad.models;

import java.util.ArrayList;
import java.util.List;

public class Squad {
  private int id;
  private int maxSize;
  private String name;
  private String cause;
  private List<Hero> heroes;

  public Squad(int maxSize, String name, String cause) {
    this.id = 0;
    this.maxSize = maxSize;
    this.name = name;
    this.cause = cause;
    this.heroes = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getMaxSize() {
    return maxSize;
  }

  public String getCause() {
    return cause;
  }

  public List<Hero> getHeroes() {
    return heroes;
  }
}
