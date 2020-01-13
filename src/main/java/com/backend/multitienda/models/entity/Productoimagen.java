package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Productoimagen {
    private int idProductoImagen;
    private String imagenProducto;
    private Producto productoByIdProducto;
    private boolean estado;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_imagen", nullable = false)
    public int getIdProductoImagen() {
        return idProductoImagen;
    }

    public void setIdProductoImagen(int idProductoImagen) {
        this.idProductoImagen = idProductoImagen;
    }

    @Basic
    @Column(name = "imagen_producto", nullable = false, length = -1)
    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
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
        Productoimagen that = (Productoimagen) o;
        return idProductoImagen == that.idProductoImagen &&
                Objects.equals(imagenProducto, that.imagenProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProductoImagen, imagenProducto);
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
        return "Productoimagen{" +
          "idProductoImagen=" + idProductoImagen +
          ", imagenProducto='" + imagenProducto + '\'' +
          ", productoByIdProducto=" + productoByIdProducto +
          ", estado=" + estado +
          '}';
    }
}
