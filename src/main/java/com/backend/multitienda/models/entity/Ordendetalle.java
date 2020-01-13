package com.backend.multitienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Ordendetalle {
    private int idOrdenDetalle;
    private int cantidadProducto;
    private Producto productoByIdProducto;
    private boolean estado;

    @JsonIgnore
    private Collection<Ordencabecera> ordencabecerasByIdOrdenDetalle;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden_detalle", nullable = false)
    public int getIdOrdenDetalle() {
        return idOrdenDetalle;
    }

    public void setIdOrdenDetalle(int idOrdenDetalle) {
        this.idOrdenDetalle = idOrdenDetalle;
    }

    @Basic
    @Column(name = "cantidad_producto", nullable = false)
    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
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
        Ordendetalle that = (Ordendetalle) o;
        return idOrdenDetalle == that.idOrdenDetalle &&
                cantidadProducto == that.cantidadProducto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrdenDetalle, cantidadProducto);
    }

    @OneToMany(mappedBy = "ordendetalleByIdOrdenDetalle")
    public Collection<Ordencabecera> getOrdencabecerasByIdOrdenDetalle() {
        return ordencabecerasByIdOrdenDetalle;
    }

    public void setOrdencabecerasByIdOrdenDetalle(Collection<Ordencabecera> ordencabecerasByIdOrdenDetalle) {
        this.ordencabecerasByIdOrdenDetalle = ordencabecerasByIdOrdenDetalle;
    }

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", nullable = false)
    public Producto getProductoByIdProducto() {
        return productoByIdProducto;
    }

    public void setProductoByIdProducto(Producto productoByIdProducto) {
        this.productoByIdProducto = productoByIdProducto;
    }

    @Override
    public String toString() {
        return "Ordendetalle{" +
          "idOrdenDetalle=" + idOrdenDetalle +
          ", cantidadProducto=" + cantidadProducto +
          ", productoByIdProducto=" + productoByIdProducto +
          ", estado=" + estado +
          ", ordencabecerasByIdOrdenDetalle=" + ordencabecerasByIdOrdenDetalle +
          '}';
    }
}
