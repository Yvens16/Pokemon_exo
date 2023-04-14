package com.pokemon.demo.dto;

public class AddPokemonDTO {
  
  private String name;
  private String type;
  private Long vetoId;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public Long getVetoId() {
    return vetoId;
  }
  public void setVetoId(Long vetoId) {
    this.vetoId = vetoId;
  }
  
}
