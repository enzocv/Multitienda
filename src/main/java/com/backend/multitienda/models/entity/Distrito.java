package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Distrito {
    private int idDistrito;
    private String nombreDistrito;
    private String estado;

    private Ciudad ciudadByIdCiudad;

    @ManyToOne
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad", nullable = false)
    public Ciudad getCiudadByIdCiudad() {
        return ciudadByIdCiudad;
    }

    public void setCiudadByIdCiudad(Ciudad ciudadByIdCiudad) {
        this.ciudadByIdCiudad = ciudadByIdCiudad;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        Distrito distrito = (Distrito) o;
        return idDistrito == distrito.idDistrito &&
                Objects.equals(nombreDistrito, distrito.nombreDistrito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDistrito, nombreDistrito);
    }

    @Override
    public String toString() {
        return "Distrito{" +
          "idDistrito=" + idDistrito +
          ", nombreDistrito='" + nombreDistrito + '\'' +
          ", estado=" + estado +
          '}';
    }
}
