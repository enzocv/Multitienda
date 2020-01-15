package com.backend.multitienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class EstadoOrden {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_estado_orden", nullable = false)
  private int idEstadoOrden;

  @Basic
  @Column(name = "descripcion_estado_orden", nullable = false, length = 50)
  private String descripcionEstadoOrden;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @JsonIgnore
  @OneToMany(mappedBy = "estadoOrden")
  private Collection<OrdenCabecera> ordenCabeceras;

}
