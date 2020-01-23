package com.backend.multitienda.dto;

import lombok.Data;

@Data
public class OrdenDetalleDto {

  private int idOrdenDetalle;

  private int cantidadProducto;

  private String estado;

  private int idProducto;

  private int idOrdenCabecera;
}
