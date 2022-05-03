package com.anna.hero_squad.services;

import com.anna.hero_squad.models.Hero;
import com.anna.hero_squad.models.Squad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class HeroService implements HeroSquadService<Hero, Squad>{
  private List<Hero> heroes = new ArrayList<>();

  /**
   * Function to add hero data for storage in a list of heroes
   * @param data Hero data to be added to hero list
   * @param collection Updated list of heroes
   */
  @Override
  public void add(Hero data, List<Hero> collection) {
    heroes = Objects.requireNonNullElseGet(collection, (Supplier<List<Hero>>) ArrayList::new);
    data.setId(heroes.size() + 1);
    heroes.add(data);
  }

  /**
   * Function to retrieve a list of heroes
   * @return A list of heroes
   */
  @Override
  public List<Hero> getAll() {
    return heroes;
  }
  /**
   * Function to retrieve a hero's data from the updated list of heroes
   * @param id A hero's id
   * @param collection The updated list of heroes
   * @return Hero data if hero is found, otherwise null
   */
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

  /**
   * Function to update a hero's data
   * @param data A hero's updated data
   * @param collection The updated list of heroes
   */
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

  /**
   * Function to delete hero data
   * @param id A hero's id
   * @param collection The updated list of heroes
   * @param squads The updated list of squads
   */
  @Override
  public void delete(int id, List<Hero> collection, List<Squad> squads) {
    SquadService squadService = new SquadService();
    Hero hero = get(id, collection);
    if(hero.getSquadId() != 0){
      Squad heroSquad = squadService.get(hero.getSquadId(), squads);
      // Perform clean up
      heroSquad.getHeroes().remove(hero);
    }
    collection.remove(id - 1);
    heroes = collection;
  }
}
