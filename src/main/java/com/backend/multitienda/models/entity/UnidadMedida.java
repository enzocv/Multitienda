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
  @Column(name = "descripcion_unidad_medida", nullable = false)
  private String descripcionUnidadMedida;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @OneToMany(mappedBy = "unidadMedida")
  @JsonIgnore
  private Collection<Producto> productos;

}
