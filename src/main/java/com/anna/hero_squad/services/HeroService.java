package com.anna.hero_squad.services;

import com.anna.hero_squad.models.Hero;

import java.util.ArrayList;
import java.util.List;

public class HeroService implements HeroSquadService<Hero>{
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
    return collection.get(id - 1);
  }

  @Override
  public void update(Hero data) {

  }

  @Override
  public void delete(int id) {

  }

  @Override
  public void deleteAll() {

  }
}
