package com.backend.multitienda.dto;

import lombok.Data;

@Data
public class StockProductoDto {

  private int idStockProducto;

  private int stockProducto;

  private String estado;

  private int idProducto;

  private int idSede;
}
