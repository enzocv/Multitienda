package com.backend.multitienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class Pais {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_pais", nullable = false)
  private int idPais;

  @Basic
  @Column(name = "nombre_pais", nullable = false, length = 50)
  private String nombrePais;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @JsonIgnore
  @OneToMany(mappedBy = "pais")
  private Collection<Ciudad> ciudades;

}
