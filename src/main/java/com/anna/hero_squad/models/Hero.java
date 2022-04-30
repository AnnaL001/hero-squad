package com.anna.hero_squad.models;

public class Hero {
  private int id;
  private String name;
  private int age;
  private String specialPower;
  private String weakness;

  private char gender;
  private int squadId;

  public Hero(String name, int age, String specialPower, String weakness, char gender) {
    this.id = 0;
    this.name = name;
    this.age = age;
    this.specialPower = specialPower;
    this.weakness = weakness;
    this.gender = gender;
    this.squadId = 0;
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

  public char getGender() { return gender; }

  public void setGender(char gender) { this.gender = gender; }

  public void setSquadId(int squadId) { this.squadId = squadId; }
}
