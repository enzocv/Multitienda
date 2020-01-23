package com.backend.multitienda.dto;

import lombok.Data;

@Data
public class SedeDto {

  private int idSede;

  private String nombreSede;

  private String direccionSede;

  private String estado;

  private int idEmpresa;

  private int idDistrito;
}
