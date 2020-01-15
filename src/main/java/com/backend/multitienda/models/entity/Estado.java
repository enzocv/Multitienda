package com.backend.multitienda.models.entity;

public enum Estado {
  ACTIVO( "A"),
  INACTIVO( "I");

  private final String name;

  Estado(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }
}
