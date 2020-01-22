package com.backend.multitienda.models.entity;

import com.backend.multitienda.audit.Auditable;
import com.backend.multitienda.listeners.DistribuidorEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(DistribuidorEntityListener.class)
public class OrdenCabecera extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_orden_cabecera", nullable = false)
  private int idOrdenCabecera;

  @Basic
  @Column(name = "fecha_orden_realizada", nullable = false)
  private Date fechaOrdenRealizada;

  @Basic
  @Column(name = "fecha_pago_realizada", nullable = false)
  private Date fechaPagoRealizada;

  @Basic
  @Column(name = "precio_total_orden_cabecera", nullable = false, precision = 2)
  private BigDecimal precioTotalOrdenCabecera;

  @Basic
  @Column(name = "comenario_orden_cabecera", nullable = false)
  private String comenarioOrdenCabecera;

  @Basic
  @Column(name = "estado", nullable = false, length = 1, columnDefinition = "CHAR")
  private String estado;

  @JsonIgnore
  @OneToMany(mappedBy = "idOrdenCabecera")
  private Collection<OrdenDetalle> ordenDetalles;

  @ManyToOne
  @JoinColumn(name = "id_distribuidor", referencedColumnName = "id_distribuidor", nullable = false)
  private Distribuidor distribuidor;

  @ManyToOne
  @JoinColumn(name = "id_sede", referencedColumnName = "id_sede", nullable = false)
  private Sede sede;

  @ManyToOne
  @JoinColumn(name = "id_estado_orden", referencedColumnName = "id_estado_orden", nullable = false)
  private EstadoOrden estadoOrden;

}
