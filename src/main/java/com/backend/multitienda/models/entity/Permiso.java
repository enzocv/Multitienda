package com.backend.multitienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Permiso {
    private int idPermiso;
    private String descripcionPermiso;
    @JsonIgnore
    private Collection<Usuario> usuariosByIdPermiso;

    public Permiso(){}

    public Permiso(int idPermiso, String descripcionPermiso) {
        super();
        this.idPermiso = idPermiso;
        this.descripcionPermiso = descripcionPermiso;
    }

    @Id
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
}
