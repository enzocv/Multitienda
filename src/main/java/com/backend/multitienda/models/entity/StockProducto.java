package com.backend.multitienda.models.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class StockProducto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_stock_producto", nullable = false)
  private int idStockProducto;

  @Basic
  @Column(name = "stock_producto", nullable = false)
  private int stockProducto;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @ManyToOne
  @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", nullable = false)
  private Producto producto;

  @ManyToOne
  @JoinColumn(name = "id_sede", referencedColumnName = "id_sede", nullable = false)
  private Sede sede;

}
