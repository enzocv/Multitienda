package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Stockproducto {
    private int idStockProducto;
    private int stockProducto;
    private boolean estado;
    private Producto productoByIdProducto;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock_producto", nullable = false)
    public int getIdStockProducto() {
        return idStockProducto;
    }

    public void setIdStockProducto(int idStockProducto) {
        this.idStockProducto = idStockProducto;
    }

    @Basic
    @Column(name = "stock_producto", nullable = false)
    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    @Basic
    @Column(name = "estado", length = 1, columnDefinition = "BIT")
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
        Stockproducto that = (Stockproducto) o;
        return idStockProducto == that.idStockProducto &&
                stockProducto == that.stockProducto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStockProducto, stockProducto, estado);
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
        return "Stockproducto{" +
          "idStockProducto=" + idStockProducto +
          ", stockProducto=" + stockProducto +
          ", estado=" + estado +
          ", productoByIdProducto=" + productoByIdProducto +
          '}';
    }
}