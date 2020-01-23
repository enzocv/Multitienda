package com.backend.multitienda.dto;

import lombok.Data;


@Data
public class CiudadDto {

  private int idCiudad;

  private String nombreCiudad;

  private String estado;

  private int idPais;
}
