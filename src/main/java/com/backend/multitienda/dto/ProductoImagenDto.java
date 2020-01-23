package com.backend.multitienda.dto;

import lombok.Data;

@Data
public class ProductoImagenDto {

  private int idProductoImagen;

  private String imagenProducto;

  private String estado;

  private int idProducto;
}
