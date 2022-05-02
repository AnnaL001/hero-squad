package com.anna.hero_squad.services;

import com.anna.hero_squad.models.Hero;
import com.anna.hero_squad.models.Squad;
import com.anna.hero_squad.parameter_resolver.HeroParameterResolver;
import com.anna.hero_squad.parameter_resolver.HeroServiceParameterResolver;
import com.anna.hero_squad.parameter_resolver.SquadParameterResolver;
import com.anna.hero_squad.parameter_resolver.SquadServiceParameterResolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SquadParameterResolver.class)
@ExtendWith(SquadServiceParameterResolver.class)
@ExtendWith(HeroParameterResolver.class)
@ExtendWith(HeroServiceParameterResolver.class)
class SquadServiceTest {
  @Test
  @DisplayName("Test to check squad is added to squads list")
  public void add_addsSquadInstance_true(Squad squad, SquadService squadService) {
    squadService.add(squad, squadService.getAll());
    assertTrue(squadService.getAll().contains(squad));
  }

  @Test
  @DisplayName("Test to check all added squads can be retrieved")
  public void getAll_retrievesHeroesList_true(Squad squad, SquadService squadService) {
    Squad anotherSquad = setUpASquad();
    squadService.add(squad, squadService.getAll());
    squadService.add(anotherSquad, squadService.getAll());
    assertEquals(2, squadService.getAll().size());
  }

  @Test
  @DisplayName("Test to check empty list is returned if no squad is added")
  public void getAll_retrievesEmptyListIfNoHeroes_true(SquadService squadService) {
    assertEquals(0, squadService.getAll().size());
  }

  @Test
  @DisplayName("Test to check squad can be retrieved from squads list")
  public void get_retrievesHeroInHeroesList_true(Squad squad, SquadService squadService) {
    squadService.add(squad, squadService.getAll());
    Squad foundSquad = squadService.get(squad.getId(), squadService.getAll());
    assertEquals(squad, foundSquad);
  }

  @Test
  @DisplayName("Test to check that null value is returned when a squad is not found")
  public void get_returnsNullIfSquadNotFound_true(Squad squad, SquadService squadService) {
    Squad foundSquad = squadService.get(squad.getId(), squadService.getAll());
    assertNull(foundSquad);
  }

  @Test
  @DisplayName("Test to check that squad data is updated as specified")
  public void update_updatesHeroInfo_true(Squad squad, SquadService squadService) {
    squadService.add(squad, squadService.getAll());
    squad.setName("Avengers Endgame");
    squad.setMaxSize(20);
    squad.setCause("Reverse the wiping out of the universe population");
    squadService.update(squad, squadService.getAll());
    Squad foundSquad = squadService.get(squad.getId(), squadService.getAll());
    assertEquals("Avengers Endgame", foundSquad.getName());
    assertEquals(20, foundSquad.getMaxSize());
    assertEquals("Reverse the wiping out of the universe population", foundSquad.getCause());
  }

  @Test
  @DisplayName("Test to check that squad data can be deleted successfully")
  public void delete_deletesASquad_false(Squad squad, SquadService squadService, HeroService heroService) {
    squadService.add(squad, squadService.getAll());
    squadService.delete(squad.getId(), squadService.getAll(), heroService.getAll());
    assertFalse(squadService.getAll().contains(squad));
  }

  @Test
  @DisplayName("Test to check that squad heroes' squadId is updated correctly on squad deletion")
  public void delete_updatesSquadHeroesSquadId_false(Hero hero, Squad squad, SquadService squadService, HeroService heroService) {
    heroService.add(hero, heroService.getAll());
    squadService.add(squad, squadService.getAll());
    squadService.addHeroToSquad(hero, squad.getId(), squadService.getAll(), heroService.getAll());
    squadService.delete(squad.getId(), squadService.getAll(), heroService.getAll());
    Hero foundHero = heroService.get(hero.getId(), heroService.getAll());
    assertEquals(0, foundHero.getSquadId());
  }

  @Test
  @DisplayName("Test to check that squad heroes list contains an added hero's data")
  public void addHeroToSquad_addsHeroToSquadHeroesList_true(Hero hero, Squad squad, SquadService squadService, HeroService heroService){
    heroService.add(hero, heroService.getAll());
    squadService.add(squad, squadService.getAll());
    squadService.addHeroToSquad(hero, squad.getId(), squadService.getAll(), heroService.getAll());
    Squad foundSquad = squadService.get(squad.getId(), squadService.getAll());
    assertTrue(foundSquad.getHeroes().contains(hero));
  }

  @Test
  @DisplayName("Test to check that hero's squadId is updated correctly after being added to a squad")
  public void addHeroToSquad_updatesHeroSquadId_true(Hero hero, Squad squad, SquadService squadService, HeroService heroService){
    heroService.add(hero, heroService.getAll());
    squadService.add(squad, squadService.getAll());
    squadService.addHeroToSquad(hero, squad.getId(), squadService.getAll(), heroService.getAll());
    Hero foundHero = heroService.get(hero.getId(), heroService.getAll());
    assertEquals(1, foundHero.getSquadId());
  }

  @Test
  @DisplayName("Test to check that squad heroes list does not contain hero data when hero is removed")
  public void deleteHeroFromSquad_deletesHeroFromSquadHeroesList_false(Hero hero, Squad squad, SquadService squadService, HeroService heroService){
    heroService.add(hero, heroService.getAll());
    squadService.add(squad, squadService.getAll());
    squadService.addHeroToSquad(hero, squad.getId(), squadService.getAll(), heroService.getAll());
    Squad foundSquad = squadService.get(squad.getId(), squadService.getAll());
    squadService.deleteHeroFromSquad(hero, foundSquad.getId(), squadService.getAll(), heroService.getAll());
    assertFalse(foundSquad.getHeroes().contains(hero));
  }

  @Test
  @DisplayName("Test to check that hero's squadId is updated correctly when hero is removed from squad")
  public void deleteHeroFromSquad_updatesHeroSquadId_true(Hero hero, Squad squad, SquadService squadService, HeroService heroService){
    heroService.add(hero, heroService.getAll());
    squadService.add(squad, squadService.getAll());
    squadService.addHeroToSquad(hero, squad.getId(), squadService.getAll(), heroService.getAll());
    squadService.deleteHeroFromSquad(hero, squad.getId(), squadService.getAll(), heroService.getAll());
    Hero foundHero = heroService.get(hero.getId(), heroService.getAll());
    assertEquals(0, foundHero.getSquadId());
  }

  private Squad setUpASquad(){
    return new Squad(5, "Inner circle", "Fight for mutants' rights");
  }

}