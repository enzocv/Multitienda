package com.backend.multitienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Unidadmedida {
    private int idUnidadMedida;
    private String descripcionUnidadMedida;
    private boolean estado;

    @JsonIgnore
    private Collection<Producto> productosByIdUnidadMedida;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad_medida", nullable = false)
    public int getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(int idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    @Basic
    @Column(name = "descripcion_unidad_medida", nullable = false, length = 5)
    public String getDescripcionUnidadMedida() {
        return descripcionUnidadMedida;
    }

    public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
        this.descripcionUnidadMedida = descripcionUnidadMedida;
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
        Unidadmedida that = (Unidadmedida) o;
        return idUnidadMedida == that.idUnidadMedida &&
                Objects.equals(descripcionUnidadMedida, that.descripcionUnidadMedida) &&
                Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUnidadMedida, descripcionUnidadMedida, estado);
    }

    @OneToMany(mappedBy = "unidadmedidaByIdUnidadMedida")
    public Collection<Producto> getProductosByIdUnidadMedida() {
        return productosByIdUnidadMedida;
    }

    public void setProductosByIdUnidadMedida(Collection<Producto> productosByIdUnidadMedida) {
        this.productosByIdUnidadMedida = productosByIdUnidadMedida;
    }

    @Override
    public String toString() {
        return "Unidadmedida{" +
          "idUnidadMedida=" + idUnidadMedida +
          ", descripcionUnidadMedida='" + descripcionUnidadMedida + '\'' +
          ", estado=" + estado +
          ", productosByIdUnidadMedida=" + productosByIdUnidadMedida +
          '}';
    }
}
