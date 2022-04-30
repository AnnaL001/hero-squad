package com.anna.hero_squad.models;

import com.anna.hero_squad.parameter_resolver.HeroParameterResolver;
import com.anna.hero_squad.parameter_resolver.SquadParameterResolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SquadParameterResolver.class)
@ExtendWith(HeroParameterResolver.class)
class SquadTest {
  @Test
  @DisplayName("Test that a squad instance is instantiated correctly")
  public void newSquad_instantiatesCorrectly_true(Squad squad) {
    assertNotNull(squad);
  }

  @Test
  @DisplayName("Test that squad instance is instantiated with default id")
  public void newSquad_instantiatesWithDefaultId_true(Squad squad) {
    assertEquals(0, squad.getId());
  }

  @Test
  @DisplayName("Test that a squad instance instantiates with the name property's value")
  public void newSquad_instantiatesWithName_true(Squad squad) {
    assertEquals("The Avengers", squad.getName());
  }

  @Test
  @DisplayName("Test that a squad instance instantiates with the maxSize property's value")
  public void newSquad_instantiatesWithMaxSize_true(Squad squad) {
    assertEquals(6, squad.getMaxSize());
  }

  @Test
  @DisplayName("Test that a squad instance instantiates with the maxSize property's value")
  public void newSquad_instantiatesWithCause_true(Squad squad) {
    assertEquals("Stop Thanos from clearing the universe's population", squad.getCause());
  }

  @Test
  @DisplayName("Test that a squad instance instantiates with an empty list of heroes")
  public void newSquad_instantiatesWithEmptyHeroesList_true(Squad squad) {
    assertEquals(new ArrayList<>(), squad.getHeroes());
  }

  @Test
  @DisplayName("Test that a squad instance's id is updated as specified")
  public void setId_updatesIDCorrectly_true(Squad squad) {
    squad.setId(1);
    assertEquals(1, squad.getId());
  }

  @Test
  @DisplayName("Test that a squad instance's name is updated as specified")
  public void setName_updatesNameCorrectly_true(Squad squad) {
    squad.setName("Avengers Endgame");
    assertEquals("Avengers Endgame", squad.getName());
  }

  @Test
  @DisplayName("Test that a squad instance's hero list's maximum size is updated as specified")
  public void setMaxSize_updatesMaxSizeCorrectly_true(Squad squad) {
    squad.setMaxSize(5);
    assertEquals(5, squad.getMaxSize());
  }

  @Test
  @DisplayName("Test that a squad instance's hero list's maximum size is updated as specified")
  public void setCause_updatesMaxSizeCorrectly_true(Squad squad) {
    squad.setMaxSize(5);
    assertEquals(5, squad.getMaxSize());
  }

  @Test
  @DisplayName("Test that a squad's cause is updated as specified")
  public void setCause_updatesCauseCorrectly_true(Squad squad) {
    squad.setCause("Stop Thanos and children");
    assertEquals("Stop Thanos and children", squad.getCause());
  }

  @Test
  @DisplayName("Test that a squad's list of heroes is updated as specified")
  public void setHeroList_updatesHeroesListCorrectly_true(Squad squad, Hero hero) {
    List<Hero> heroes = new ArrayList<>(List.of(hero));
    squad.setHeroes(heroes);
    assertEquals(heroes, squad.getHeroes());
  }
}