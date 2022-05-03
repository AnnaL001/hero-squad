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

  /**
   * Function to squad data for storage in list of squads
   * @param data Squad data to be added to hero list
   * @param collection The updated list of squads
   */
  @Override
  public void add(Squad data, List<Squad> collection) {
    if(collection != null){
      squads = collection;
    } else {
      squads = new ArrayList<>();
    }
    data.setId(squads.size() + 1);
    squads.add(data);
  }

  /**
   * Function to retrieve list of squads
   * @return List of squads
   */
  @Override
  public List<Squad> getAll() {
    return squads;
  }

  /**
   * Function to retrieve a squad's data from the list of squads
   * @param id A squad's id
   * @param collection The updated list of squads
   * @return Squad data is squad is found, otherwise null
   */
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

  /**
   * Function to update a squad's data
   * @param data The updated squad data
   * @param collection The updated list of squads
   */
  @Override
  public void update(Squad data, List<Squad> collection) {
    squads = collection;
    Squad squad = get(data.getId(), squads);
    squad.setName(data.getName());
    squad.setCause(data.getCause());
    squad.setMaxSize(data.getMaxSize());
    squad.setIsFull();
  }

  /**
   * Function to add a hero to a squad
   * @param hero Hero to be added to squad
   * @param squadId The id of the squad that the hero is to be added
   * @param collection The updated list of squads
   * @param heroes The updated list of heroes
   */
  public void addHeroToSquad(Hero hero, int squadId, List<Squad> collection, List<Hero> heroes){
    squads = collection;
    Squad squad = get(squadId, squads);
    squad.setIsFull();
    if(!squad.getIsFull()){
      // Add hero to squad heroes list
      squad.getHeroes().add(hero);
      // Get added hero data
      int heroIndex = squad.getHeroes().indexOf(hero);
      Hero foundHero = squad.getHeroes().get(heroIndex);
      // Update hero squadId and isInSquad
      foundHero.setSquadId(squadId);
      foundHero.setIsInSquad(true);
      heroService.update(foundHero, heroes);
      squad.setIsFull();
    } else {
      System.out.println("Squad is full");
    }
  }

  /**
   * Function to delete a hero from a squad
   * @param hero Hero to be deleted from squad
   * @param squadId The id of the squad that the hero is to be deleted
   * @param collection The updated list of squads
   * @param heroes The updated list of heroes
   */
  public void deleteHeroFromSquad(Hero hero, int squadId, List<Squad> collection, List<Hero> heroes){
    squads = collection;
    Squad squad = get(squadId, squads);
    squad.getHeroes().remove(hero);
    // Set squad's isFull property
    squad.setIsFull();
    Hero foundHero = heroService.get(hero.getId(), heroes);
    // Update hero squadId and isInSquad property
    foundHero.setSquadId(0);
    foundHero.setIsInSquad(false);
    heroService.update(foundHero, heroes);
  }

  /**
   * Function to delete a squad
   * @param id The id of the squad to be deleted
   * @param collection The updated list of squads
   * @param heroes The updated list of heroes
   */

  @Override
  public void delete(int id, List<Squad> collection, List<Hero> heroes) {
    Squad squad = get(id, collection);
    List<Hero> heroList = squad.getHeroes();
    // Update hero's squadId and isInSquad property
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
