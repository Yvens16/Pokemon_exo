package com.pokemon.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemon.demo.entity.Veto;

@Repository
public interface VetoRepository extends JpaRepository<Veto, Long> {
  
  Veto findByName(String name);
}
