package com.backend.multitienda.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
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
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @ManyToOne
  @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad", nullable = false)
  private Ciudad ciudad;

  @JsonIgnore
  @OneToMany(mappedBy = "idDistrito")
  private Collection<Empresa> empresas;

  @JsonIgnore
  @OneToMany(mappedBy = "idDistrito")
  private Collection<Sede> sedes;

}
