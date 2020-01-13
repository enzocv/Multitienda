package com.backend.multitienda.models.entity;

import com.backend.multitienda.audit.Auditable;
import com.backend.multitienda.listeners.UsuarioEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Entity
@EntityListeners(UsuarioEntityListener.class)
public class Usuario extends Auditable<String> {
  private int idUsuario;
  private String emailUsuario;
  private String password;
  private Permiso permiso;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Usuario usuario = (Usuario) o;
    return idUsuario == usuario.idUsuario &&
      Objects.equals(emailUsuario, usuario.emailUsuario) &&
      Objects.equals(password, usuario.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idUsuario, emailUsuario, password);
  }

  @OneToMany(mappedBy = "usuarioByIdUsuario")
  @JsonIgnore
  public Collection<Distribuidor> getDistribuidorsByIdUsuario() {
    return distribuidorsByIdUsuario;
  }

  public void setDistribuidorsByIdUsuario(Collection<Distribuidor> distribuidorsByIdUsuario) {
    this.distribuidorsByIdUsuario = distribuidorsByIdUsuario;
  }

  @OneToMany(mappedBy = "usuarioByIdUsuario")
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
      ", distribuidorsByIdUsuario=" + distribuidorsByIdUsuario +
      ", proveedorsByIdUsuario=" + proveedorsByIdUsuario +
      '}';
  }
}
