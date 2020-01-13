package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Unidadmedida {
    private int idUnidadMedida;
    private String descripcionUnidadMedida;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unidadmedida that = (Unidadmedida) o;
        return idUnidadMedida == that.idUnidadMedida &&
                Objects.equals(descripcionUnidadMedida, that.descripcionUnidadMedida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUnidadMedida, descripcionUnidadMedida);
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
                ", productosByIdUnidadMedida=" + productosByIdUnidadMedida +
                '}';
    }
}
