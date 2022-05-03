package com.anna.hero_squad.services;

import com.anna.hero_squad.models.Hero;
import com.anna.hero_squad.models.Squad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class SquadService implements HeroSquadService<Squad, Hero> {
  private List<Squad> squads = new ArrayList<>();
  private final HeroService heroService = new HeroService();
  @Override
  public void add(Squad data, List<Squad> collection) {
    squads = Objects.requireNonNullElseGet(collection, (Supplier<List<Squad>>) ArrayList::new);
    data.setId(squads.size() + 1);
    squads.add(data);
  }

  @Override
  public List<Squad> getAll() {
    return squads;
  }

  @Override
  public Squad get(int id, List<Squad> collection) {
    squads = collection;
    try {
      return squads.get(id - 1);
    } catch(Exception exception){
      System.out.println("Squad not found\n" + exception.getMessage());
      return null;
    }
  }

  @Override
  public void update(Squad data, List<Squad> collection) {
    squads = collection;
    Squad squad = get(data.getId(), squads);
    squad.setName(data.getName());
    squad.setCause(data.getCause());
    squad.setMaxSize(data.getMaxSize());
    squad.setIsFull();
  }

  public void addHeroToSquad(Hero hero, int squadId, List<Squad> collection, List<Hero> heroes){
    squads = collection;
    Squad squad = get(squadId, squads);
    if(!squad.getIsFull()){
      // Add hero to squad heroes list
      squad.getHeroes().add(hero);
      // Get added hero data
      int heroIndex = squad.getHeroes().indexOf(hero);
      Hero foundHero = squad.getHeroes().get(heroIndex);
      // Update hero squadId
      foundHero.setSquadId(squadId);
      foundHero.setIsInSquad(true);
      heroService.update(foundHero, heroes);
      squad.setIsFull();
    } else {
      System.out.println("Squad is full");
    }
  }

  public void deleteHeroFromSquad(Hero hero, int squadId, List<Squad> collection, List<Hero> heroes){
    squads = collection;
    Squad squad = get(squadId, squads);
    squad.getHeroes().remove(hero);
    squad.setIsFull();
    Hero foundHero = heroService.get(hero.getId(), heroes);
    foundHero.setSquadId(0);
    foundHero.setIsInSquad(false);
    heroService.update(foundHero, heroes);
  }

  @Override
  public void delete(int id, List<Squad> collection, List<Hero> heroes) {
    Squad squad = get(id, collection);
    List<Hero> heroList = squad.getHeroes();
    // Set squadId of heroes in squad heroes list to 0
    for(Hero hero: heroList){
      hero.setSquadId(0);
      hero.setIsInSquad(false);
      heroService.update(hero, heroes);
    }

    if(!collection.isEmpty()){
      collection.remove(id - 1);
    }

    // Retrieve updated list
    squads = collection;
  }

}
