package com.backend.multitienda.models.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProductoImagen {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_producto_imagen", nullable = false)
  private int idProductoImagen;

  @Basic
  @Column(name = "imagen_producto", columnDefinition = "TEXT", nullable = false)
  private String imagenProducto;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @ManyToOne
  @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", nullable = false)
  private Producto producto;

}
