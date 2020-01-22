package com.backend.multitienda.models.entity;

import com.backend.multitienda.audit.Auditable;
import com.backend.multitienda.listeners.CategoriaEmpresaEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@EntityListeners(CategoriaEmpresaEntityListener.class)
public class CategoriaEmpresa extends Auditable<String> {

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

  public CategoriaEmpresa(int idCategoriaEmpresa){
    this.idCategoriaEmpresa = idCategoriaEmpresa;
  }

}
