package com.backend.multitienda.models.entity;

import com.backend.multitienda.audit.Auditable;
import com.backend.multitienda.listeners.SedeEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(SedeEntityListener.class)
public class Sede extends Auditable<String> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_sede", nullable = false)
  private int idSede;

  @Basic
  @Column(name = "nombre_sede", nullable = false, length = 50)
  private String nombreSede;

  @Basic
  @Column(name = "direccion_sede", nullable = false)
  private String direccionSede;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @ManyToOne
  @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false)
  private Empresa empresa;

  @JsonIgnore
  @OneToMany(mappedBy = "sede")
  private Collection<OrdenCabecera> ordenCabeceras;

  @OneToMany(mappedBy = "sede")
  private Collection<StockProducto> stockProductos;

  @ManyToOne
  @JoinColumn(name = "id_distrito", referencedColumnName = "id_distrito", nullable = false)
  private Distrito idDistrito;

}
