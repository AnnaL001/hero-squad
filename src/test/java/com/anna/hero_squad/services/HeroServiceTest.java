package com.anna.hero_squad.services;

import com.anna.hero_squad.models.Hero;
import com.anna.hero_squad.parameter_resolver.HeroParameterResolver;
import com.anna.hero_squad.parameter_resolver.HeroServiceParameterResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(HeroParameterResolver.class)
@ExtendWith(HeroServiceParameterResolver.class)
class HeroServiceTest {

  @Test
  void add_addsHeroInstance_true(Hero hero, HeroService heroService) {
    heroService.add(hero);
    assertTrue(heroService.getAll().contains(hero));
  }
}