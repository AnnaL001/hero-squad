package com.anna.hero_squad.services;

import com.anna.hero_squad.models.Squad;
import com.anna.hero_squad.parameter_resolver.SquadParameterResolver;
import com.anna.hero_squad.parameter_resolver.SquadServiceParameterResolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SquadParameterResolver.class)
@ExtendWith(SquadServiceParameterResolver.class)
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
    Squad anotherSquad = new Squad(5, "Inner circle", "Fight for mutants' rights");
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
    Squad foundSquad = squadService.get(1, squadService.getAll());
    assertEquals(squad, foundSquad);
  }
}