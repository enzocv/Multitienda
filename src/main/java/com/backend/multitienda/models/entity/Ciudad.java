package com.backend.multitienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class Ciudad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ciudad", nullable = false)
  private int idCiudad;

  @Basic
  @Column(name = "nombre_ciudad", nullable = false, length = 50)
  private String nombreCiudad;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @ManyToOne
  @JoinColumn(name = "id_pais", referencedColumnName = "id_pais", nullable = false)
  private Pais pais;

  @JsonIgnore
  @OneToMany(mappedBy = "ciudad")
  private Collection<Distrito> distritos;

}
