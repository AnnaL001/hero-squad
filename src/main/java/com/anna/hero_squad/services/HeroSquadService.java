package com.anna.hero_squad.services;


import java.util.List;

public interface HeroSquadService<T> {
  // CREATE
  void add(T data, List<T> collection);

  // READ
  List<T> getAll();
  T get(int id, List<T> collection);

  // UPDATE
  void update(T data);

  // DELETE
  void delete(int id);
  void deleteAll();
}
