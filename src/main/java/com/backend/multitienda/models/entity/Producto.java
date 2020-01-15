package com.backend.multitienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Data
@Entity
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_producto", nullable = false)
  private int idProducto;

  @Basic
  @Column(name = "nombre_producto", nullable = false, length = 50)
  private String nombreProducto;

  @Basic
  @Column(name = "descripcion_producto", nullable = false)
  private String descripcionProducto;

  @Basic
  @Column(name = "precio_unitario", nullable = false, precision = 2)
  private BigDecimal precioUnitario;

  @Basic
  @Column(name = "precio_empaque", nullable = false, precision = 2)
  private BigDecimal precioEmpaque;

  @Basic
  @Column(name = "precio_por_mayor", nullable = false, precision = 2)
  private BigDecimal precioPorMayor;

  @Basic
  @Column(name = "igv_producto", nullable = false)
  private boolean igvProducto;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @ManyToOne
  @JoinColumn(name = "id_empaque", referencedColumnName = "id_empaque", nullable = false)
  private Empaque empaque;

  @ManyToOne
  @JoinColumn(name = "id_unidad_medida", referencedColumnName = "id_unidad_medida", nullable = false)
  private Unidadmedida unidadMedida;

  @ManyToOne
  @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false)
  private Empresa empresa;

  @ManyToOne
  @JoinColumn(name = "id_categoria_producto", referencedColumnName = "id_categoria_producto", nullable = false)
  private CategoriaProducto categoriaProducto;

  @JsonIgnore
  @OneToMany(mappedBy = "producto")
  private Collection<OrdenDetalle> ordenesDetalles;

  @JsonIgnore
  @OneToMany(mappedBy = "producto")
  private Collection<Productoimagen> productosImagenes;

  @JsonIgnore
  @OneToMany(mappedBy = "producto")
  private Collection<StockProducto> stocksProductos;

}
