package com.anna.hero_squad.services;

import com.anna.hero_squad.models.Hero;

import java.util.List;

public interface HeroSquadService<T> {
  // CREATE
  void add(T data);

  // READ
  List<T> getAll();
  T get(int id);

  // UPDATE
  void update(T data);

  // DELETE
  void delete(int id);
  void deleteAll();
}
