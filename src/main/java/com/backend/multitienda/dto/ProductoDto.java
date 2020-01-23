package com.backend.multitienda.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDto {

  private int idProducto;

  private String nombreProducto;

  private String descripcionProducto;

  private BigDecimal precioUnitario;

  private BigDecimal precioEmpaque;

  private BigDecimal precioPorMayor;

  private boolean igvProducto;

  private String estado;

  private int idEmpaque;

  private int idUnidadMedida;

  private int idEmpresa;

  private int idCategoriaProducto;

}
