package com.backend.multitienda.models.entity;

import com.backend.multitienda.audit.Auditable;
import com.backend.multitienda.listeners.EmpaqueEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Data
@Entity
@EntityListeners(EmpaqueEntityListener.class)
public class Empaque extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_empaque", nullable = false)
  private int idEmpaque;

  @Basic
  @Column(name = "descripcion_empaque", nullable = false)
  private String descripcionEmpaque;

  @Basic
  @Column(name = "precio_empaque", nullable = false, precision = 2)
  private BigDecimal precioEmpaque;

  @Basic
  @Column(name = "cantidad_producto_empaque", nullable = false)
  private int cantidadProductoEmpaque;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @JsonIgnore
  @OneToMany(mappedBy = "empaque")
  private Collection<Producto> productos;

}
