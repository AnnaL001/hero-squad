package com.anna.hero_squad.models;

import com.anna.hero_squad.models.parameter_resolver.HeroParameterResolver;
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
  @DisplayName("Test that a hero instance instantiates with the specialPower's property's value")
  public void newHero_instantiatesWithSpecialPowers_true(Hero hero) {
    assertEquals("Magnetokinesis", hero.getSpecialPower());
  }

  @Test
  public void newHero_instantiatesWithWeakness_true(Hero hero) {
    assertEquals("Can't use powers without metal in close range", hero.getWeakness());
  }

}