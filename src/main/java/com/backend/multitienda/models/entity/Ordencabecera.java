package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Ordencabecera {
    private int idOrdenCabecera;
    private Date fechaOrdenRealizada;
    private Date fechaPagoRealizada;
    private BigDecimal precioTotalOrdenCabecera;
    private String comenarioOrdenCabecera;
    private Ordendetalle ordendetalleByIdOrdenDetalle;
    private Distribuidor distribuidorByIdDistribuidor;
    private Sede sedeByIdSede;
    private Estadoorden estadoordenByIdEstadoOrden;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden_cabecera", nullable = false)
    public int getIdOrdenCabecera() {
        return idOrdenCabecera;
    }

    public void setIdOrdenCabecera(int idOrdenCabecera) {
        this.idOrdenCabecera = idOrdenCabecera;
    }

    @Basic
    @Column(name = "fecha_orden_realizada", nullable = false)
    public Date getFechaOrdenRealizada() {
        return fechaOrdenRealizada;
    }

    public void setFechaOrdenRealizada(Date fechaOrdenRealizada) {
        this.fechaOrdenRealizada = fechaOrdenRealizada;
    }

    @Basic
    @Column(name = "fecha_pago_realizada", nullable = false)
    public Date getFechaPagoRealizada() {
        return fechaPagoRealizada;
    }

    public void setFechaPagoRealizada(Date fechaPagoRealizada) {
        this.fechaPagoRealizada = fechaPagoRealizada;
    }

    @Basic
    @Column(name = "precio_total_orden_cabecera", nullable = false, precision = 2)
    public BigDecimal getPrecioTotalOrdenCabecera() {
        return precioTotalOrdenCabecera;
    }

    public void setPrecioTotalOrdenCabecera(BigDecimal precioTotalOrdenCabecera) {
        this.precioTotalOrdenCabecera = precioTotalOrdenCabecera;
    }

    @Basic
    @Column(name = "comenario_orden_cabecera", nullable = false, length = -1)
    public String getComenarioOrdenCabecera() {
        return comenarioOrdenCabecera;
    }

    public void setComenarioOrdenCabecera(String comenarioOrdenCabecera) {
        this.comenarioOrdenCabecera = comenarioOrdenCabecera;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ordencabecera that = (Ordencabecera) o;
        return idOrdenCabecera == that.idOrdenCabecera &&
                Objects.equals(fechaOrdenRealizada, that.fechaOrdenRealizada) &&
                Objects.equals(fechaPagoRealizada, that.fechaPagoRealizada) &&
                Objects.equals(precioTotalOrdenCabecera, that.precioTotalOrdenCabecera) &&
                Objects.equals(comenarioOrdenCabecera, that.comenarioOrdenCabecera);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrdenCabecera, fechaOrdenRealizada, fechaPagoRealizada, precioTotalOrdenCabecera, comenarioOrdenCabecera);
    }

    @ManyToOne
    @JoinColumn(name = "id_orden_detalle", referencedColumnName = "id_orden_detalle", nullable = false)
    public Ordendetalle getOrdendetalleByIdOrdenDetalle() {
        return ordendetalleByIdOrdenDetalle;
    }

    public void setOrdendetalleByIdOrdenDetalle(Ordendetalle ordendetalleByIdOrdenDetalle) {
        this.ordendetalleByIdOrdenDetalle = ordendetalleByIdOrdenDetalle;
    }

    @ManyToOne
    @JoinColumn(name = "id_distribuidor", referencedColumnName = "id_distribuidor", nullable = false)
    public Distribuidor getDistribuidorByIdDistribuidor() {
        return distribuidorByIdDistribuidor;
    }

    public void setDistribuidorByIdDistribuidor(Distribuidor distribuidorByIdDistribuidor) {
        this.distribuidorByIdDistribuidor = distribuidorByIdDistribuidor;
    }

    @ManyToOne
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede", nullable = false)
    public Sede getSedeByIdSede() {
        return sedeByIdSede;
    }

    public void setSedeByIdSede(Sede sedeByIdSede) {
        this.sedeByIdSede = sedeByIdSede;
    }

    @ManyToOne
    @JoinColumn(name = "id_estado_orden", referencedColumnName = "id_estado_orden", nullable = false)
    public Estadoorden getEstadoordenByIdEstadoOrden() {
        return estadoordenByIdEstadoOrden;
    }

    public void setEstadoordenByIdEstadoOrden(Estadoorden estadoordenByIdEstadoOrden) {
        this.estadoordenByIdEstadoOrden = estadoordenByIdEstadoOrden;
    }

    @Override
    public String toString() {
        return "Ordencabecera{" +
                "idOrdenCabecera=" + idOrdenCabecera +
                ", fechaOrdenRealizada=" + fechaOrdenRealizada +
                ", fechaPagoRealizada=" + fechaPagoRealizada +
                ", precioTotalOrdenCabecera=" + precioTotalOrdenCabecera +
                ", comenarioOrdenCabecera='" + comenarioOrdenCabecera + '\'' +
                ", ordendetalleByIdOrdenDetalle=" + ordendetalleByIdOrdenDetalle +
                ", distribuidorByIdDistribuidor=" + distribuidorByIdDistribuidor +
                ", sedeByIdSede=" + sedeByIdSede +
                ", estadoordenByIdEstadoOrden=" + estadoordenByIdEstadoOrden +
                '}';
    }
}
