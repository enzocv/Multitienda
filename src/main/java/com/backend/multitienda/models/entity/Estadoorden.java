package com.backend.multitienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Estadoorden {
    private int idEstadoOrden;
    private String descripcionEstadoOrden;
    private String estado;

    @JsonIgnore
    private Collection<Ordencabecera> ordencabecerasByIdEstadoOrden;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_orden", nullable = false)
    public int getIdEstadoOrden() {
        return idEstadoOrden;
    }

    public void setIdEstadoOrden(int idEstadoOrden) {
        this.idEstadoOrden = idEstadoOrden;
    }

    @Basic
    @Column(name = "descripcion_estado_orden", nullable = false, length = 50)
    public String getDescripcionEstadoOrden() {
        return descripcionEstadoOrden;
    }

    public void setDescripcionEstadoOrden(String descripcionEstadoOrden) {
        this.descripcionEstadoOrden = descripcionEstadoOrden;
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
        Estadoorden that = (Estadoorden) o;
        return idEstadoOrden == that.idEstadoOrden &&
                Objects.equals(descripcionEstadoOrden, that.descripcionEstadoOrden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstadoOrden, descripcionEstadoOrden);
    }

    @OneToMany(mappedBy = "estadoordenByIdEstadoOrden")
    public Collection<Ordencabecera> getOrdencabecerasByIdEstadoOrden() {
        return ordencabecerasByIdEstadoOrden;
    }

    public void setOrdencabecerasByIdEstadoOrden(Collection<Ordencabecera> ordencabecerasByIdEstadoOrden) {
        this.ordencabecerasByIdEstadoOrden = ordencabecerasByIdEstadoOrden;
    }

    @Override
    public String toString() {
        return "Estadoorden{" +
          "idEstadoOrden=" + idEstadoOrden +
          ", descripcionEstadoOrden='" + descripcionEstadoOrden + '\'' +
          ", estado=" + estado +
          ", ordencabecerasByIdEstadoOrden=" + ordencabecerasByIdEstadoOrden +
          '}';
    }
}
