package com.backend.multitienda.dto;

import lombok.Data;

@Data
public class EmpresaDto {

  private int idEmpresa;

  private String nombreEmpresa;

  private String rucEmpresa;

  private String telefonoEmpresa;

  private String direccionEmpresa;

  private String emailEmpresa;

  private String estado;

  private int idCategoriaEmpresa;

  private int idDistrito;
}
