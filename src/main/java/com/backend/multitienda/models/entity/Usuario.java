package com.backend.multitienda.models.entity;

import com.backend.multitienda.audit.Auditable;
import com.backend.multitienda.listeners.UsuarioEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@EntityListeners(UsuarioEntityListener.class)
public class Usuario extends Auditable<String> {
  private int idUsuario;
  private String emailUsuario;
  private String password;
  private Permiso permiso;
  private boolean estado;

  @JsonIgnore
  private Collection<Distribuidor> distribuidorsByIdUsuario;
  private Collection<Proveedor> proveedorsByIdUsuario;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario", nullable = false)
  public int getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
  }

  @Basic
  @Column(name = "email_usuario", nullable = true, length = -1)
  public String getEmailUsuario() {
    return emailUsuario;
  }

  public void setEmailUsuario(String emailUsuario) {
    this.emailUsuario = emailUsuario;
  }

  @Basic
  @Column(name = "password", nullable = true, length = -1)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Basic
  @Column(name = "estado", nullable = true, length = 1, columnDefinition = "BIT")
  public boolean getEstado() {
    return estado;
  }

  public void setEstado(boolean estado) {
    this.estado = estado;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Usuario usuario = (Usuario) o;
    return idUsuario == usuario.idUsuario &&
      Objects.equals(emailUsuario, usuario.emailUsuario) &&
      Objects.equals(password, usuario.password) &&
      Objects.equals(estado, usuario.estado);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idUsuario, emailUsuario, password, estado);
  }

  @OneToMany(mappedBy = "usuarioByIdUsuario")
  @JsonIgnore
  public Collection<Distribuidor> getDistribuidorsByIdUsuario() {
    return distribuidorsByIdUsuario;
  }

  public void setDistribuidorsByIdUsuario(Collection<Distribuidor> distribuidorsByIdUsuario) {
    this.distribuidorsByIdUsuario = distribuidorsByIdUsuario;
  }

  @OneToMany(mappedBy = "usuario")
  @JsonIgnore
  public Collection<Proveedor> getProveedorsByIdUsuario() {
    return proveedorsByIdUsuario;
  }

  public void setProveedorsByIdUsuario(Collection<Proveedor> proveedorsByIdUsuario) {
    this.proveedorsByIdUsuario = proveedorsByIdUsuario;
  }

  @ManyToOne
  @JoinColumn(name = "id_permiso", referencedColumnName = "id_permiso", nullable = true)
  public Permiso getPermiso() {
    return permiso;
  }

  public void setPermiso(Permiso permiso) {

    this.permiso = permiso;
  }

  @Override
  public String toString() {
    return "Usuario{" +
      "idUsuario=" + idUsuario +
      ", emailUsuario='" + emailUsuario + '\'' +
      ", password='" + password + '\'' +
      ", permiso=" + permiso +
      ", estado=" + estado +
      ", distribuidorsByIdUsuario=" + distribuidorsByIdUsuario +
      ", proveedorsByIdUsuario=" + proveedorsByIdUsuario +
      '}';
  }
}
