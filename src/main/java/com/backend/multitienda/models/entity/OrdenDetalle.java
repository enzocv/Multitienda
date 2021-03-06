package com.backend.multitienda.models.entity;

import com.backend.multitienda.audit.Auditable;
import com.backend.multitienda.listeners.OrdenDetalleEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners({OrdenDetalleEntityListener.class})
public class OrdenDetalle extends Auditable<String> {

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
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @ManyToOne
  @JoinColumn(name = "id_orden_cabecera", referencedColumnName = "id_orden_cabecera", nullable = false)
  private OrdenCabecera idOrdenCabecera;

}
