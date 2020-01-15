package com.backend.multitienda.models.entity;

import com.backend.multitienda.audit.Auditable;
import com.backend.multitienda.listeners.DistribuidorEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(DistribuidorEntityListener.class)
public class Distribuidor extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_distribuidor", nullable = false)
  private int idDistribuidor;

  @Basic
  @Column(name = "nombre_empresa_distribuidor", nullable = false, length = 50)
  private String nombreEmpresaDistribuidor;

  @Basic
  @Column(name = "nombre_distribuidor", nullable = false, length = 50)
  private String nombreDistribuidor;


  @Basic
  @Column(name = "apellido_distribuidor", nullable = false, length = 50)
  private String apellidoDistribuidor;

  @Basic
  @Column(name = "direccion_distribuidor", nullable = false)
  private String direccionDistribuidor;


  @Basic
  @Column(name = "email_distribuidor", nullable = false, length = 50)
  private String emailDistribuidor;

  @Basic
  @Column(name = "ruc_distribuidor", nullable = false, length = 11)
  private String rucDistribuidor;


  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @ManyToOne
  @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
  private Usuario usuario;

  @JsonIgnore
  @OneToMany(mappedBy = "distribuidor")
  private Collection<OrdenCabecera> ordenesCabeceras;

}
