package com.pokemon.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GenerationType;

@Entity
public class Veto {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "veto")
  private List<Pokemon> pokemons;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Pokemon> getPokemons() {
    return pokemons;
  }

  public void setPokemons(List<Pokemon> pokemons) {
    this.pokemons = pokemons;
  }

  // public Veto(String name) {
  //   this.name = name;
  // }
  
}
