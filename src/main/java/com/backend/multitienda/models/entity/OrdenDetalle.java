package com.backend.multitienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class OrdenDetalle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_orden_detalle", nullable = false)
  private int idOrdenDetalle;

  @Basic
  @Column(name = "cantidad_producto", nullable = false)
  private int cantidadProducto;

  @ManyToOne
  @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", nullable = false)
  private Producto producto;

  @Basic
  @Column(name = "estado", nullable = true, length = 1, columnDefinition = "CHAR")
  private String estado;

  @JsonIgnore
  @OneToMany(mappedBy = "ordenDetalle")
  private Collection<OrdenCabecera> ordenCabeceras;

}