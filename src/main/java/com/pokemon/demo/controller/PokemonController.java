package com.pokemon.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.demo.dto.AddPokemonDTO;
import com.pokemon.demo.entity.Pokemon;
import com.pokemon.demo.entity.Veto;
import com.pokemon.demo.repository.PokemonRepository;
import com.pokemon.demo.repository.VetoRepository;

@RestController
public class PokemonController {

  @Autowired
  VetoRepository vetoRepository;

  @Autowired
  PokemonRepository pokemonRepository;

  @GetMapping("hello")
  public String hello() {
    return "hello";
  }

  @PostMapping("add/veto")
  public String addveto(@RequestBody Veto veto) {
    // Veto newVeto = new Veto(veto.getName());
    // Recupère le nom que j'envoie à travers la requête http (@RequestBody Veto
    // veto)
    String nameNewVeto = veto.getName();

    // Je créee une entité Veto que je vais pouvoir enregistrer en base
    Veto newVeto = new Veto();

    // Je lui met un nom
    newVeto.setName(nameNewVeto);

    // Je sauvegarder
    vetoRepository.save(newVeto);
    return "veto created";
  }

  @PostMapping("add/pokemon")
  public String addPokemon(@RequestBody AddPokemonDTO pokemon) {

    // Le veto à ajouter
    Veto veto = vetoRepository.findById(pokemon.getVetoId()).get();

    Pokemon pokemonToSave = new Pokemon();
    pokemonToSave.setName(pokemon.getName());
    pokemonToSave.setType(pokemon.getType());

    // Ici j'ajoute la clé étrangère à mon pokemon
    pokemonToSave.setVeto(veto);

    pokemonRepository.save(pokemonToSave);
    // pokemonRepository
    return "pokemon added";
  }

  // Explication exacte à trouver du trnasactional
  @Transactional
  @DeleteMapping("delete/pokemon")
  public String pokemonToDelete(@RequestBody Pokemon pokemon) {
    String name = pokemon.getName();
    // Pokemon pokemonToDelete = pokemonRepository.findByName(name);
    System.out.println("@@@@@@@@: " + name);
    pokemonRepository.deleteByName(name);
    // pokemonRepository.delete(pokemonToDelete);
    return "pokemon deleted";
  }

  @DeleteMapping("delete/veto")
  public String vetoToDelete(@RequestBody Veto veto) {

    // select * from veto where name = ?
    Veto vetoToDelete = vetoRepository.findByName(veto.getName());

    for (Pokemon pokemon : vetoToDelete.getPokemons()) {
      // Supprimer la référence du veto dans le pokemon
      pokemon.setVeto(null);
    }

    vetoRepository.delete(vetoToDelete);
    return "veto deleted";
  }

  @GetMapping("pokemons")
  List<Pokemon> getPokemons() {
    // A checker pour renvoyer la clé étrangère du veto
    // Peut être lié au JsonIgnore ?
    return pokemonRepository.findAll();
  }

  @GetMapping("vetos")
  List<Veto> getVetos() {
    return vetoRepository.findAll();
  }

}
