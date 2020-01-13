package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Categoriaproducto {
    private int idCategoriaProducto;
    private String nombreCategoriaProducto;
    private String imagenCategoriaProducto;
    private Collection<Producto> productosByIdCategoriaProducto;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_producto", nullable = false)
    public int getIdCategoriaProducto() {
        return idCategoriaProducto;
    }

    public void setIdCategoriaProducto(int idCategoriaProducto) {
        this.idCategoriaProducto = idCategoriaProducto;
    }

    @Basic
    @Column(name = "nombre_categoria_producto", nullable = false, length = 50)
    public String getNombreCategoriaProducto() {
        return nombreCategoriaProducto;
    }

    public void setNombreCategoriaProducto(String nombreCategoriaProducto) {
        this.nombreCategoriaProducto = nombreCategoriaProducto;
    }

    @Basic
    @Column(name = "imagen_categoria_producto", nullable = false, length = -1)
    public String getImagenCategoriaProducto() {
        return imagenCategoriaProducto;
    }

    public void setImagenCategoriaProducto(String imagenCategoriaProducto) {
        this.imagenCategoriaProducto = imagenCategoriaProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoriaproducto that = (Categoriaproducto) o;
        return idCategoriaProducto == that.idCategoriaProducto &&
                Objects.equals(nombreCategoriaProducto, that.nombreCategoriaProducto) &&
                Objects.equals(imagenCategoriaProducto, that.imagenCategoriaProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoriaProducto, nombreCategoriaProducto, imagenCategoriaProducto);
    }

    @OneToMany(mappedBy = "categoriaproductoByIdCategoriaProducto")
    public Collection<Producto> getProductosByIdCategoriaProducto() {
        return productosByIdCategoriaProducto;
    }

    public void setProductosByIdCategoriaProducto(Collection<Producto> productosByIdCategoriaProducto) {
        this.productosByIdCategoriaProducto = productosByIdCategoriaProducto;
    }

    @Override
    public String toString() {
        return "Categoriaproducto{" +
                "idCategoriaProducto=" + idCategoriaProducto +
                ", nombreCategoriaProducto='" + nombreCategoriaProducto + '\'' +
                ", imagenCategoriaProducto='" + imagenCategoriaProducto + '\'' +
                ", productosByIdCategoriaProducto=" + productosByIdCategoriaProducto +
                '}';
    }
}
