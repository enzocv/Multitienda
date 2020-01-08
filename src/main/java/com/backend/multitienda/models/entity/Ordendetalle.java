package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Ordendetalle {
    private int idOrdenDetalle;
    private int cantidadProducto;
    private Collection<Ordencabecera> ordencabecerasByIdOrdenDetalle;
    private Producto productoByIdProducto;

    @Id
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
}
