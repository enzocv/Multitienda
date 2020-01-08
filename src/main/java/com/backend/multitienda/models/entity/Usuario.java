package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Usuario {
    private int idUsuario;
    private String emailUsuario;
    private String password;
    private Timestamp fechaCreacion;
    private Collection<Distribuidor> distribuidorsByIdUsuario;
    private Collection<Proveedor> proveedorsByIdUsuario;
    private Permiso permisoByIdPermiso;

    @Id
    @Column(name = "id_usuario", nullable = false)
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Basic
    @Column(name = "email_usuario", nullable = false, length = -1)
    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    @Basic
    @Column(name = "password", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "fecha_creacion", nullable = true)
    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario &&
                Objects.equals(emailUsuario, usuario.emailUsuario) &&
                Objects.equals(password, usuario.password) &&
                Objects.equals(fechaCreacion, usuario.fechaCreacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, emailUsuario, password, fechaCreacion);
    }

    @OneToMany(mappedBy = "usuarioByIdUsuario")
    public Collection<Distribuidor> getDistribuidorsByIdUsuario() {
        return distribuidorsByIdUsuario;
    }

    public void setDistribuidorsByIdUsuario(Collection<Distribuidor> distribuidorsByIdUsuario) {
        this.distribuidorsByIdUsuario = distribuidorsByIdUsuario;
    }

    @OneToMany(mappedBy = "usuarioByIdUsuario")
    public Collection<Proveedor> getProveedorsByIdUsuario() {
        return proveedorsByIdUsuario;
    }

    public void setProveedorsByIdUsuario(Collection<Proveedor> proveedorsByIdUsuario) {
        this.proveedorsByIdUsuario = proveedorsByIdUsuario;
    }

    @ManyToOne
    @JoinColumn(name = "id_permiso", referencedColumnName = "id_permiso", nullable = false)
    public Permiso getPermisoByIdPermiso() {
        return permisoByIdPermiso;
    }

    public void setPermisoByIdPermiso(Permiso permisoByIdPermiso) {
        this.permisoByIdPermiso = permisoByIdPermiso;
    }
}
