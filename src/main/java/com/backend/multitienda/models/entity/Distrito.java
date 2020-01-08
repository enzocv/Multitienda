package com.backend.multitienda.models.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Distrito {
    private int idDistrito;
    private String nombreDistrito;

    @Id
    @Column(name = "id_distrito", nullable = false)
    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    @Basic
    @Column(name = "nombre_distrito", nullable = false, length = 50)
    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distrito distrito = (Distrito) o;
        return idDistrito == distrito.idDistrito &&
                Objects.equals(nombreDistrito, distrito.nombreDistrito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDistrito, nombreDistrito);
    }
}
