package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Productoimagen {
    private int idProductoImagen;
    private int idProducto;
    private String imagenProducto;
    private Producto productoByIdProducto;

    @Id
    @Column(name = "id_producto_imagen", nullable = false)
    public int getIdProductoImagen() {
        return idProductoImagen;
    }

    public void setIdProductoImagen(int idProductoImagen) {
        this.idProductoImagen = idProductoImagen;
    }

    @Basic
    @Column(name = "id_producto", nullable = false)
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Basic
    @Column(name = "imagen_producto", nullable = false, length = -1)
    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productoimagen that = (Productoimagen) o;
        return idProductoImagen == that.idProductoImagen &&
                idProducto == that.idProducto &&
                Objects.equals(imagenProducto, that.imagenProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProductoImagen, idProducto, imagenProducto);
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
