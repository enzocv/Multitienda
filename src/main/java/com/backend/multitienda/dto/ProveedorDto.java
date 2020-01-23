package com.backend.multitienda.dto;


import lombok.Data;

@Data
public class ProveedorDto {

  private int idProveedor;

  private String nombreProveedor;

  private String apellidoProveedor;

  private String rucProveedor;

  private String estado;

  private int idUsuario;

  private int idEmpresa;
}
