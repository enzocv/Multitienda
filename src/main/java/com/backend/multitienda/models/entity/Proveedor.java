package com.backend.multitienda.models.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Proveedor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_proveedor", nullable = false)
  private int idProveedor;

  @Basic
  @Column(name = "nombre_proveedor", nullable = false, length = 50)
  private String nombreProveedor;

  @Basic
  @Column(name = "apellido_proveedor", nullable = false, length = 50)
  private String apellidoProveedor;

  @Basic
  @Column(name = "ruc_proveedor", nullable = false, length = 11)
  private String rucProveedor;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @ManyToOne
  @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
  private Usuario usuario;

  @ManyToOne
  @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false)
  private Empresa empresa;

}
