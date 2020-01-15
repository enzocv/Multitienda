package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Distrito {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_distrito", nullable = false)
  private int idDistrito;

  @Basic
  @Column(name = "nombre_distrito", nullable = false, length = 50)
  private String nombreDistrito;

  @Basic
  @Column(name = "estado", nullable = true, length = 1, columnDefinition = "CHAR")
  private String estado;

  @ManyToOne
  @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad", nullable = false)
  private Ciudad ciudad;
}
