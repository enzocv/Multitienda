package com.backend.multitienda.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class OrdenCabeceraDto {

  private int idOrdenCabecera;

  private Date fechaOrdenRealizada;

  private Date fechaPagoRealizada;

  private BigDecimal precioTotalOrdenCabecera;

  private String comenarioOrdenCabecera;

  private String estado;

  private int idDistribuidor;

  private int idSede;

  private int idEstadoOrden;
}
