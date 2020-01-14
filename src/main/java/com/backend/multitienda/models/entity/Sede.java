package com.backend.multitienda.models.entity;

import com.backend.multitienda.audit.Auditable;
import com.backend.multitienda.listeners.SedeEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@EntityListeners(SedeEntityListener.class)
public class Sede extends Auditable<String> {
    private int idSede;
    private String nombreSede;
    private String direccionSede;
    private Empresa empresaByIdEmpresa;
    private Pais paisByIdPais;
    private String estado;

    @JsonIgnore
    private Collection<Ordencabecera> ordencabecerasByIdSede;
    private Collection<Stockproducto> stockproductosByIdSede;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sede", nullable = false)
    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    @Basic
    @Column(name = "nombre_sede", nullable = false, length = 50)
    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    @Basic
    @Column(name = "direccion_sede", nullable = false, length = -1)
    public String getDireccionSede() {
        return direccionSede;
    }

    public void setDireccionSede(String direccionSede) {
        this.direccionSede = direccionSede;
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
        Sede sede = (Sede) o;
        return idSede == sede.idSede &&
                Objects.equals(nombreSede, sede.nombreSede) &&
                Objects.equals(direccionSede, sede.direccionSede);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSede, nombreSede, direccionSede);
    }

    @OneToMany(mappedBy = "sedeByIdSede")
    public Collection<Ordencabecera> getOrdencabecerasByIdSede() {
        return ordencabecerasByIdSede;
    }

    public void setOrdencabecerasByIdSede(Collection<Ordencabecera> ordencabecerasByIdSede) {
        this.ordencabecerasByIdSede = ordencabecerasByIdSede;
    }

    //relacion Sede StockProducto
    @OneToMany(mappedBy = "sedeByIdSede")
    public Collection<Stockproducto> getStockProductosByIdSede() {
        return stockproductosByIdSede;
    }

    public void setStockProductosByIdSede(Collection<Stockproducto> stockproductosByIdSede) {
        this.stockproductosByIdSede = stockproductosByIdSede;
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
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais", nullable = false)
    public Pais getPaisByIdPais() {
        return paisByIdPais;
    }

    public void setPaisByIdPais(Pais paisByIdPais) {
        this.paisByIdPais = paisByIdPais;
    }

    @Override
    public String toString() {
        return "Sede{" +
          "idSede=" + idSede +
          ", nombreSede='" + nombreSede + '\'' +
          ", direccionSede='" + direccionSede + '\'' +
          ", empresaByIdEmpresa=" + empresaByIdEmpresa +
          ", paisByIdPais=" + paisByIdPais +
          ", estado=" + estado +
          ", ordencabecerasByIdSede=" + ordencabecerasByIdSede +
          '}';
    }
}
