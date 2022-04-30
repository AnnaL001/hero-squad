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


}