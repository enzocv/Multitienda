package com.backend.multitienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Data
@Entity
public class CategoriaProducto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_categoria_producto", nullable = false)
  private int idCategoriaProducto;

  @Basic
  @Column(name = "nombre_categoria_producto", nullable = false, length = 50)
  private String nombreCategoriaProducto;

  @Basic
  @Column(name = "imagen_categoria_producto", nullable = false)
  private String imagenCategoriaProducto;

  @Basic
  @Column(name = "estado", nullable = false, columnDefinition = "CHAR", length = 1)
  private String estado;

  @JsonIgnore
  @OneToMany(mappedBy = "categoriaProducto")
  private Collection<Producto> productos;
}
