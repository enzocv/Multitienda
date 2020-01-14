package com.backend.multitienda.models.entity;

import com.backend.multitienda.audit.Auditable;
import com.backend.multitienda.listeners.PermisoEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@EntityListeners(PermisoEntityListener.class)
public class Permiso extends Auditable<String> {
    private int idPermiso;
    private String descripcionPermiso;
    private String estado;

    @JsonIgnore
    private Collection<Usuario> usuariosByIdPermiso;

    public Permiso(){}

    public Permiso(int idPermiso, String descripcionPermiso) {
        super();
        this.idPermiso = idPermiso;
        this.descripcionPermiso = descripcionPermiso;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso", nullable = false)
    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    @Basic
    @Column(name = "descripcion_permiso", nullable = false, length = 50)
    public String getDescripcionPermiso() {
        return descripcionPermiso;
    }

    public void setDescripcionPermiso(String descripcionPermiso) {
        this.descripcionPermiso = descripcionPermiso;
    }

    @Basic
    @Column(name = "estado", nullable = true, length = 1, columnDefinition = "CHAR")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permiso permiso = (Permiso) o;
        return idPermiso == permiso.idPermiso &&
                Objects.equals(descripcionPermiso, permiso.descripcionPermiso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPermiso, descripcionPermiso);
    }

    @OneToMany(mappedBy = "permiso")
    public Collection<Usuario> getUsuariosByIdPermiso() {
        return usuariosByIdPermiso;
    }

    public void setUsuariosByIdPermiso(Collection<Usuario> usuariosByIdPermiso) {
        this.usuariosByIdPermiso = usuariosByIdPermiso;
    }

    @Override
    public String toString() {
        return "Permiso{" +
          "idPermiso=" + idPermiso +
          ", descripcionPermiso='" + descripcionPermiso + '\'' +
          ", estado=" + estado +
          ", usuariosByIdPermiso=" + usuariosByIdPermiso +
          '}';
    }
}
