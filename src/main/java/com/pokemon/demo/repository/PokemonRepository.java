package com.pokemon.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemon.demo.entity.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

  // Explication exacte à trouver du trnasactional
  @Transactional
  void deleteByName(String name);

  Pokemon findByName(String name);
}
