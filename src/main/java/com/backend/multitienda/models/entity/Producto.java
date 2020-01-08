package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Producto {
    private int idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private BigDecimal precioUnitario;
    private BigDecimal precioEmpaque;
    private BigDecimal precioPorMayor;
    private int idEmpaque;
    private int idUnidadMedida;
    private int idEmpresa;
    private int idCategoriaProducto;
    private boolean igvProducto;
    private Collection<Ordendetalle> ordendetallesByIdProducto;
    private Empaque empaqueByIdEmpaque;
    private Unidadmedida unidadmedidaByIdUnidadMedida;
    private Empresa empresaByIdEmpresa;
    private Categoriaproducto categoriaproductoByIdCategoriaProducto;
    private Collection<Productoimagen> productoimagensByIdProducto;
    private Collection<Stockproducto> stockproductosByIdProducto;

    @Id
    @Column(name = "id_producto", nullable = false)
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Basic
    @Column(name = "nombre_producto", nullable = false, length = 50)
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    @Basic
    @Column(name = "descripcion_producto", nullable = false, length = -1)
    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    @Basic
    @Column(name = "precio_unitario", nullable = false, precision = 2)
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Basic
    @Column(name = "precio_empaque", nullable = false, precision = 2)
    public BigDecimal getPrecioEmpaque() {
        return precioEmpaque;
    }

    public void setPrecioEmpaque(BigDecimal precioEmpaque) {
        this.precioEmpaque = precioEmpaque;
    }

    @Basic
    @Column(name = "precio_por_mayor", nullable = false, precision = 2)
    public BigDecimal getPrecioPorMayor() {
        return precioPorMayor;
    }

    public void setPrecioPorMayor(BigDecimal precioPorMayor) {
        this.precioPorMayor = precioPorMayor;
    }

    @Basic
    @Column(name = "id_empaque", nullable = false)
    public int getIdEmpaque() {
        return idEmpaque;
    }

    public void setIdEmpaque(int idEmpaque) {
        this.idEmpaque = idEmpaque;
    }

    @Basic
    @Column(name = "id_unidad_medida", nullable = false)
    public int getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(int idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    @Basic
    @Column(name = "id_empresa", nullable = false)
    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Basic
    @Column(name = "id_categoria_producto", nullable = false)
    public int getIdCategoriaProducto() {
        return idCategoriaProducto;
    }

    public void setIdCategoriaProducto(int idCategoriaProducto) {
        this.idCategoriaProducto = idCategoriaProducto;
    }

    @Basic
    @Column(name = "igv_producto", nullable = false)
    public boolean isIgvProducto() {
        return igvProducto;
    }

    public void setIgvProducto(boolean igvProducto) {
        this.igvProducto = igvProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return idProducto == producto.idProducto &&
                idEmpaque == producto.idEmpaque &&
                idUnidadMedida == producto.idUnidadMedida &&
                idEmpresa == producto.idEmpresa &&
                idCategoriaProducto == producto.idCategoriaProducto &&
                igvProducto == producto.igvProducto &&
                Objects.equals(nombreProducto, producto.nombreProducto) &&
                Objects.equals(descripcionProducto, producto.descripcionProducto) &&
                Objects.equals(precioUnitario, producto.precioUnitario) &&
                Objects.equals(precioEmpaque, producto.precioEmpaque) &&
                Objects.equals(precioPorMayor, producto.precioPorMayor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, nombreProducto, descripcionProducto, precioUnitario, precioEmpaque, precioPorMayor, idEmpaque, idUnidadMedida, idEmpresa, idCategoriaProducto, igvProducto);
    }

    @OneToMany(mappedBy = "productoByIdProducto")
    public Collection<Ordendetalle> getOrdendetallesByIdProducto() {
        return ordendetallesByIdProducto;
    }

    public void setOrdendetallesByIdProducto(Collection<Ordendetalle> ordendetallesByIdProducto) {
        this.ordendetallesByIdProducto = ordendetallesByIdProducto;
    }

    @ManyToOne
    @JoinColumn(name = "id_empaque", referencedColumnName = "id_empaque", nullable = false)
    public Empaque getEmpaqueByIdEmpaque() {
        return empaqueByIdEmpaque;
    }

    public void setEmpaqueByIdEmpaque(Empaque empaqueByIdEmpaque) {
        this.empaqueByIdEmpaque = empaqueByIdEmpaque;
    }

    @ManyToOne
    @JoinColumn(name = "id_unidad_medida", referencedColumnName = "id_unidad_medida", nullable = false)
    public Unidadmedida getUnidadmedidaByIdUnidadMedida() {
        return unidadmedidaByIdUnidadMedida;
    }

    public void setUnidadmedidaByIdUnidadMedida(Unidadmedida unidadmedidaByIdUnidadMedida) {
        this.unidadmedidaByIdUnidadMedida = unidadmedidaByIdUnidadMedida;
    }

    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false)
    public Empresa getEmpresaByIdEmpresa() {
        return empresaByIdEmpresa;
    }

    public void setEmpresaByIdEmpresa(Empresa empresaByIdEmpresa) {
        this.empresaByIdEmpresa = empresaByIdEmpresa;
    }

    @ManyToOne
    @JoinColumn(name = "id_categoria_producto", referencedColumnName = "id_categoria_producto", nullable = false)
    public Categoriaproducto getCategoriaproductoByIdCategoriaProducto() {
        return categoriaproductoByIdCategoriaProducto;
    }

    public void setCategoriaproductoByIdCategoriaProducto(Categoriaproducto categoriaproductoByIdCategoriaProducto) {
        this.categoriaproductoByIdCategoriaProducto = categoriaproductoByIdCategoriaProducto;
    }

    @OneToMany(mappedBy = "productoByIdProducto")
    public Collection<Productoimagen> getProductoimagensByIdProducto() {
        return productoimagensByIdProducto;
    }

    public void setProductoimagensByIdProducto(Collection<Productoimagen> productoimagensByIdProducto) {
        this.productoimagensByIdProducto = productoimagensByIdProducto;
    }

    @OneToMany(mappedBy = "productoByIdProducto")
    public Collection<Stockproducto> getStockproductosByIdProducto() {
        return stockproductosByIdProducto;
    }

    public void setStockproductosByIdProducto(Collection<Stockproducto> stockproductosByIdProducto) {
        this.stockproductosByIdProducto = stockproductosByIdProducto;
    }
}
