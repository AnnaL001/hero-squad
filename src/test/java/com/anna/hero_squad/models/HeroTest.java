package com.anna.hero_squad.models;

import com.anna.hero_squad.parameter_resolver.HeroParameterResolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(HeroParameterResolver.class)
class HeroTest {
  @Test
  @DisplayName("Test that a hero instance is instantiated correctly")
  public void newHero_instantiatesCorrectly_true(Hero hero) {
    assertNotNull(hero);
  }

  @Test
  @DisplayName("Test that a hero instance instantiates with the name property's value")
  public void newHero_instantiatesWithName_true(Hero hero) {
    assertEquals("Polaris", hero.getName());
  }

  @Test
  @DisplayName("Test that a hero instance instantiates with the age property's value")
  public void newHero_instantiatesWithAge_true(Hero hero) {
    assertEquals(18, hero.getAge());
  }

  @Test
  @DisplayName("Test that a hero instance instantiates with the specialPower property's value")
  public void newHero_instantiatesWithSpecialPowers_true(Hero hero) {
    assertEquals("Magnetokinesis", hero.getSpecialPower());
  }

  @Test
  @DisplayName("Test that a hero instance instantiates with the weakness property's value")
  public void newHero_instantiatesWithWeakness_true(Hero hero) {
    assertEquals("Can't use powers without metal in close range", hero.getWeakness());
  }

  @Test
  @DisplayName("Test that a hero instance instantiates with the gender property's value")
  public void newHero_instantiatesWithGender_true(Hero hero) {
    assertEquals('F', hero.getGender());
  }

  @Test
  @DisplayName("Test that a hero instance's name is updated as specified")
  public void setName_updatesNameCorrectly_true(Hero hero) {
    hero.setName("Lorna Dane");
    assertEquals("Lorna Dane", hero.getName());
  }

  @Test
  @DisplayName("Test that a hero instance's age is updated as specified")
  public void setAge_updatesAgeCorrectly_true(Hero hero) {
    hero.setAge(20);
    assertEquals(20, hero.getAge());
  }

  @Test
  @DisplayName("Test that a hero instance's special power is updated as specified")
  public void setSpecialPower_updatesSpecialPowerCorrectly_true(Hero hero) {
    hero.setSpecialPower("Ferromagnetism");
    assertEquals("Ferromagnetism", hero.getSpecialPower());
  }

  @Test
  @DisplayName("Test that a hero instance's weakness is updated as specified")
  public void setWeakness_updatesWeaknessCorrectly_true(Hero hero) {
    hero.setWeakness("Can't use powers secretly due to greenish-black glow around her hands");
    assertEquals("Can't use powers secretly due to greenish-black glow around her hands", hero.getWeakness());
  }

  @Test
  @DisplayName("Test that a hero instance's gender is updated as specified")
  public void setGender_updatesGenderCorrectly_true(Hero hero) {
    hero.setGender('U');
    assertEquals('U', hero.getGender());
  }
}