package com.anna.hero_squad.services;

import com.anna.hero_squad.models.Hero;
import com.anna.hero_squad.models.Squad;

import java.util.ArrayList;
import java.util.List;

public class SquadService implements HeroSquadService<Squad> {
  private List<Squad> squads = new ArrayList<>();
  private final HeroService heroService = new HeroService();
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

  public void addHeroToSquad(Hero hero, int squadId, List<Squad> collection, List<Hero> heroes){
    squads = collection;
    Squad squad = get(squadId, squads);
    // Add hero to squad heroes list
    squad.getHeroes().add(hero);
    // Get added hero data
    int heroIndex = squad.getHeroes().indexOf(hero);
    Hero foundHero = squad.getHeroes().get(heroIndex);
    // Update hero squadId
    foundHero.setSquadId(squadId);
    heroService.update(foundHero, heroes);
  }

  public void deleteHeroFromSquad(Hero hero, int squadId, List<Squad> collection, List<Hero> heroes){
    squads = collection;
    Squad squad = get(squadId, squads);
    squad.getHeroes().remove(hero);
    Hero foundHero = heroService.get(hero.getId(), heroes);
    foundHero.setSquadId(0);
    heroService.update(foundHero, heroes);
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
