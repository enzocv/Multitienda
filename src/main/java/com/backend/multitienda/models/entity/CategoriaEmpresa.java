package com.backend.multitienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
public class CategoriaEmpresa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_categoria_empresa", nullable = false)
  private int idCategoriaEmpresa;

  @Basic
  @Column(name = "descripcion_categoria_empresa", nullable = false, length = 50)
  private String descripcionCategoriaEmpresa;

  @Basic
  @Column(name = "imagen_categoria_empresa", nullable = false)
  private String imagenCategoriaEmpresa;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @JsonIgnore
  @OneToMany(mappedBy = "categoriaEmpresa")
  private Collection<Empresa> empresas;

}
