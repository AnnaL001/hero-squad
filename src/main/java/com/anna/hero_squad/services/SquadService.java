package com.anna.hero_squad.services;

import com.anna.hero_squad.models.Squad;

import java.util.ArrayList;
import java.util.List;

public class SquadService implements HeroSquadService<Squad> {
  private List<Squad> squads = new ArrayList<>();
  @Override
  public void add(Squad data, List<Squad> collection) {
    squads = collection;
    data.setId(squads.size() + 1);
    squads.add(data);
  }

  @Override
  public List<Squad> getAll() {
    return squads;
  }

  @Override
  public Squad get(int id, List<Squad> collection) {
    return collection.get(id - 1);
  }

  @Override
  public void update(Squad data, List<Squad> collection) {
    Squad squad = get(data.getId(), collection);
    squad.setName(data.getName());
    squad.setCause(data.getCause());
    squad.setMaxSize(data.getMaxSize());
  }


  @Override
  public void delete(int id, List<Squad> collection) {
    if(!collection.isEmpty()){
      collection.remove(id - 1);
    }

    // Retrieve updated list
    squads = collection;
  }

  @Override
  public void deleteAll(List<Squad> collection) {
    if(!collection.isEmpty()){
      collection.clear();
    }
    squads = collection;
  }
}
