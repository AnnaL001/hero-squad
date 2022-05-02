package com.anna.hero_squad.services;

import com.anna.hero_squad.models.Hero;
import com.anna.hero_squad.parameter_resolver.HeroParameterResolver;
import com.anna.hero_squad.parameter_resolver.HeroServiceParameterResolver;
import com.anna.hero_squad.parameter_resolver.SquadParameterResolver;
import com.anna.hero_squad.parameter_resolver.SquadServiceParameterResolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(HeroParameterResolver.class)
@ExtendWith(SquadParameterResolver.class)
@ExtendWith(HeroServiceParameterResolver.class)
@ExtendWith(SquadServiceParameterResolver.class)
class HeroServiceTest {

  @Test
  @DisplayName("Test to check hero is added to heroes list")
  public void add_addsHeroInstance_true(Hero hero, HeroService heroService) {
    heroService.add(hero, heroService.getAll());
    assertTrue(heroService.getAll().contains(hero));
  }

  @Test
  @DisplayName("Test to check all added heroes can be retrieved")
  public void getAll_retrievesHeroesList_true(Hero hero, HeroService heroService) {
    Hero anotherHero = setUpHero();
    heroService.add(hero, heroService.getAll());
    heroService.add(anotherHero, heroService.getAll());
    assertEquals(2, heroService.getAll().size());
  }

  @Test
  @DisplayName("Test to check empty list is returned if no hero is added")
  public void getAll_retrievesEmptyListIfNoHeroes_true(HeroService heroService) {
    assertEquals(0, heroService.getAll().size());
  }

  @Test
  @DisplayName("Test to check hero can be retrieved from heroes list")
  public void get_retrievesHeroInHeroesList_true(Hero hero, HeroService heroService) {
    heroService.add(hero, heroService.getAll());
    assertEquals(hero, heroService.get(1, heroService.getAll()));
  }

  @Test
  @DisplayName("Test to check that null value is returned when a hero is not found")
  public void get_returnsNullIfHeroNotFound_true(Hero hero, HeroService heroService) {
    Hero foundHero = heroService.get(hero.getId(), heroService.getAll());
    assertNull(foundHero);
  }

  @Test
  @DisplayName("Test to check that hero data is updated as specified")
  public void update_updatesHeroInfo_true(Hero hero, HeroService heroService) {
    heroService.add(hero, heroService.getAll());
    hero.setName("Lorna Dane");
    hero.setAge(20);
    hero.setSpecialPower("Ferromagnetism");
    hero.setWeakness("Can't use powers secretly due to dark-greenish glow on hands");
    hero.setGender(true);
    heroService.update(hero, heroService.getAll());
    Hero foundHero = heroService.get(hero.getId(), heroService.getAll());
    assertEquals("Lorna Dane", foundHero.getName());
    assertEquals(20, foundHero.getAge());
    assertEquals("Ferromagnetism", hero.getSpecialPower());
    assertEquals("Can't use powers secretly due to dark-greenish glow on hands", hero.getWeakness());
    assertTrue(hero.getGender());
  }

  @Test
  @DisplayName("Test to check that hero data can be deleted successfully")
  public void delete_deletesAHeroNotInSquad_false(Hero hero, HeroService heroService, SquadService squadService) {
    heroService.add(hero, heroService.getAll());
    heroService.delete(hero.getId(), heroService.getAll(), squadService.getAll());
    assertFalse(heroService.getAll().contains(hero));
  }

  @Test
  @DisplayName("Test to check that hero data can be deleted successfully")
  public void delete_updatesSquadHeroesList_false(Hero hero, HeroService heroService, SquadService squadService) {
    heroService.add(hero, heroService.getAll());
    heroService.delete(hero.getId(), heroService.getAll(), squadService.getAll());
    assertFalse(heroService.getAll().contains(hero));
  }

  private Hero setUpHero(){
    return new Hero("Captain America", 93, "Enhanced strength, reflexes and speed", "Can get mortally wounded", true);
  }
}