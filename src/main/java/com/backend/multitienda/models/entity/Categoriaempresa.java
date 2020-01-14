package com.backend.multitienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Categoriaempresa {
    private int idCategoriaEmpresa;
    private String descripcionCategoriaEmpresa;
    private String imagenCategoriaEmpresa;
    private String estado;

    @JsonIgnore
    private Collection<Empresa> empresasByIdCategoriaEmpresa;

    @OneToMany(mappedBy = "categoriaEmpresa")
    public Collection<Empresa> getEmpresasByIdCategoriaEmpresa() {
        return empresasByIdCategoriaEmpresa;
    }

    public void setEmpresasByIdCategoriaEmpresa(Collection<Empresa> empresasByIdCategoriaEmpresa) {
        this.empresasByIdCategoriaEmpresa = empresasByIdCategoriaEmpresa;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_empresa", nullable = false)
    public int getIdCategoriaEmpresa() {
        return idCategoriaEmpresa;
    }

    public void setIdCategoriaEmpresa(int idCategoriaEmpresa) {
        this.idCategoriaEmpresa = idCategoriaEmpresa;
    }

    @Basic
    @Column(name = "descripcion_categoria_empresa", nullable = false, length = 50)
    public String getDescripcionCategoriaEmpresa() {
        return descripcionCategoriaEmpresa;
    }

    public void setDescripcionCategoriaEmpresa(String descripcionCategoriaEmpresa) {
        this.descripcionCategoriaEmpresa = descripcionCategoriaEmpresa;
    }

    @Basic
    @Column(name = "imagen_categoria_empresa", nullable = false, length = -1)
    public String getImagenCategoriaEmpresa() {
        return imagenCategoriaEmpresa;
    }

    public void setImagenCategoriaEmpresa(String imagenCategoriaEmpresa) {
        this.imagenCategoriaEmpresa = imagenCategoriaEmpresa;
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
        Categoriaempresa that = (Categoriaempresa) o;
        return idCategoriaEmpresa == that.idCategoriaEmpresa &&
                Objects.equals(descripcionCategoriaEmpresa, that.descripcionCategoriaEmpresa) &&
                Objects.equals(imagenCategoriaEmpresa, that.imagenCategoriaEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoriaEmpresa, descripcionCategoriaEmpresa, imagenCategoriaEmpresa);
    }

    @Override
    public String toString() {
        return "Categoriaempresa{" +
          "idCategoriaEmpresa=" + idCategoriaEmpresa +
          ", descripcionCategoriaEmpresa='" + descripcionCategoriaEmpresa + '\'' +
          ", imagenCategoriaEmpresa='" + imagenCategoriaEmpresa + '\'' +
          ", estado=" + estado +
          ", empresasByIdCategoriaEmpresa=" + empresasByIdCategoriaEmpresa +
          '}';
    }
}
