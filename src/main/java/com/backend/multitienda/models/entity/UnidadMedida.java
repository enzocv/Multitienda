package com.backend.multitienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class UnidadMedida {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_unidad_medida", nullable = false)
  private int idUnidadMedida;

  @Basic
  @Column(name = "unidad", length = 5, nullable = false)
  private String unidad;

  @Basic
  @Column(name = "descripcion_unidad_medida",nullable = false)
  private String descripcionUnidadMedida;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @JsonIgnore
  @OneToMany(mappedBy = "unidadMedida")
  private Collection<Producto> productos;

}
