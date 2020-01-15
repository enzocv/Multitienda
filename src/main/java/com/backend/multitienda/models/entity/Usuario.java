package com.backend.multitienda.models.entity;

import com.backend.multitienda.audit.Auditable;
import com.backend.multitienda.listeners.UsuarioEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(UsuarioEntityListener.class)
public class Usuario extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario", nullable = false)
  private int idUsuario;

  @Basic
  @Column(name = "email_usuario", nullable = false, length = 50)
  private String emailUsuario;

  @Basic
  @JsonIgnore
  @Column(name = "password", nullable = false, columnDefinition = "TEXT")
  private String password;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @ManyToOne
  @JoinColumn(name = "id_permiso", referencedColumnName = "id_permiso", nullable = false)
  private Permiso permiso;

  @JsonIgnore
  @OneToMany(mappedBy = "usuario")
  private Collection<Distribuidor> distribuidores;


  @OneToMany(mappedBy = "usuario")
  @JsonIgnore
  private Collection<Proveedor> proveedores;


}
