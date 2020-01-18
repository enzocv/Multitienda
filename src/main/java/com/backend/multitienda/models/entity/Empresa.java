package com.backend.multitienda.models.entity;

import com.backend.multitienda.audit.Auditable;
import com.backend.multitienda.listeners.EmpresaEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(EmpresaEntityListener.class)
public class Empresa extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_empresa", nullable = false)
  private int idEmpresa;

  @Basic
  @Column(name = "nombre_empresa", nullable = false, length = 50)
  private String nombreEmpresa;

  @Basic
  @Column(name = "ruc_empresa", nullable = false, length = 11)
  private String rucEmpresa;

  @Basic
  @Column(name = "telefono_empresa", nullable = false, length = 9)
  private String telefonoEmpresa;

  @Basic
  @Column(name = "direccion_empresa", nullable = false)
  private String direccionEmpresa;

  @Basic
  @Column(name = "email_empresa", nullable = false, length = 50)
  private String emailEmpresa;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @ManyToOne
  @JoinColumn(name = "id_categoria_empresa", referencedColumnName = "id_categoria_empresa", nullable = false)
  private CategoriaEmpresa categoriaEmpresa;

  @ManyToOne
  @JoinColumn(name = "id_distrito", referencedColumnName = "id_distrito", nullable = false)
  private Distrito idDistrito;

}
