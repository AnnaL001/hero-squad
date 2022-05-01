package com.anna.hero_squad.services;

import com.anna.hero_squad.models.Hero;
import com.anna.hero_squad.models.Squad;

import java.util.ArrayList;
import java.util.List;

public class HeroService implements HeroSquadService<Hero, Squad>{
  private List<Hero> heroes = new ArrayList<>();

  @Override
  public void add(Hero data, List<Hero> collection) {
    heroes = collection;
    data.setId(heroes.size() + 1);
    heroes.add(data);
  }

  @Override
  public List<Hero> getAll() {
    return heroes;
  }

  @Override
  public Hero get(int id, List<Hero> collection) {
    heroes = collection;
    try {
      return heroes.get(id - 1);
    } catch (Exception exception){
      System.out.println("Hero not found\n" + exception.getMessage());
      return null;
    }
  }

  @Override
  public void update(Hero data, List<Hero> collection) {
    heroes = collection;
    Hero hero = get(data.getId(),  heroes);
    hero.setName(data.getName());
    hero.setAge(data.getAge());
    hero.setSpecialPower(data.getSpecialPower());
    hero.setWeakness(data.getWeakness());
    hero.setGender(data.getGender());
  }

  @Override
  public void delete(int id, List<Hero> collection, List<Squad> squads) {
    if(!collection.isEmpty()){
      collection.remove(id - 1);
    }
    heroes = collection;
  }
}
