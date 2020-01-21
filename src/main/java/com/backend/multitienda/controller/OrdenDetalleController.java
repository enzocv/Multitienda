package com.backend.multitienda.controller;

import com.backend.multitienda.models.entity.OrdenDetalle;
import com.backend.multitienda.repositories.IOrdenDetalleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Servicio para Orden Detalle", description = "Esta API permite realizar las operaciones basicas de Orden" +
  " Detalle")
@RestController
@RequestMapping("/api/ordenesdetalles")
public class OrdenDetalleController {
  @Autowired
  private IOrdenDetalleRepository ordenDetalleRepository;

  @GetMapping("/{idOrdenCabecera}")
  @ApiOperation(value = "Listar los Detalles de la Orden")
  public List<OrdenDetalle> getAllOrdenesDetalle(@Valid @PathVariable Integer idOrdenCabecera) {
    return ordenDetalleRepository.findByIdOrdenCabecera(idOrdenCabecera);
  }
}
