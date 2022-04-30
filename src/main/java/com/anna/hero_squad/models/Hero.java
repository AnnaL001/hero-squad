package com.anna.hero_squad.models;

public class Hero {
  private String name;
  private int age;
  private String specialPower;
  private String weakness;

  public Hero(String name, int age, String specialPower, String weakness) {
    this.name = name;
    this.age = age;
    this.specialPower = specialPower;
    this.weakness = weakness;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getSpecialPower() {
    return specialPower;
  }

  public String getWeakness() {
    return weakness;
  }
}
