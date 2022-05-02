package com.anna.hero_squad.models;

public class Hero {
  private int id;
  private String name;
  private int age;
  private String specialPower;
  private String weakness;

  private boolean gender;
  private int squadId;

  private boolean isInSquad;

  public Hero(String name, int age, String specialPower, String weakness, boolean gender) {
    this.id = 0;
    this.name = name;
    this.age = age;
    this.specialPower = specialPower;
    this.weakness = weakness;
    this.gender = gender;
    this.squadId = 0;
    this.isInSquad = false;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) { this.id = id; }
  public String getName() { return name; }
  public int getAge() { return age; }
  public String getSpecialPower() { return specialPower; }
  public String getWeakness() { return weakness; }
  public int getSquadId() { return squadId; }
  public void setName(String name) { this.name = name; }

  public void setAge(int age) { this.age = age; }

  public void setSpecialPower(String specialPower) { this.specialPower = specialPower; }

  public void setWeakness(String weakness) { this.weakness = weakness; }

  public boolean getGender() {
    return gender;
  }

  public void setGender(boolean gender) {
    this.gender = gender;
  }

  public void setSquadId(int squadId) { this.squadId = squadId; }

  public boolean getIsInSquad() {
    return isInSquad;
  }

  public void setIsInSquad(boolean isInSquad) {
    this.isInSquad = isInSquad;
  }
}
