package com.backend.multitienda.controller;

import com.backend.multitienda.dto.OrdenCabeceraDto;
import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Distribuidor;
import com.backend.multitienda.models.entity.EstadoOrden;
import com.backend.multitienda.models.entity.OrdenCabecera;
import com.backend.multitienda.models.entity.Sede;
import com.backend.multitienda.repositories.IOrdenCabeceraRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.backend.multitienda.models.entity.Estado.ACTIVO;
import static com.backend.multitienda.models.entity.Estado.INACTIVO;

@Api(value = "Servicio para Oden Cabecera", description = "Esta API permite realizar las operaciones basicas para " +
  "Orden cabecera")
@RestController
@RequestMapping("/api/ordenescabeceras")
public class OrdenCabeceraController {
  @Autowired
  private IOrdenCabeceraRepository ordenCabeceraRepository;

  @GetMapping
  @ApiOperation(value = "Listar las Cabeceras de las Ordenes")
  public List<OrdenCabecera> getAllOrdenesCabecera(){
    return ordenCabeceraRepository.findAll();
  }

  @GetMapping("/{idOrdenCabecera}")
  @ApiOperation(value = "Obtener una Cabecera de una Orden")
  public ResponseEntity<OrdenCabecera> getOrdenCabeceraById(@Valid @PathVariable Integer idOrdenCabecera) throws ResourceNotFoundException{
    OrdenCabecera findOrdenCabecera = ordenCabeceraRepository.findById(idOrdenCabecera)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro niguna Orden Cabecera con el id: " + idOrdenCabecera)
      );
    return ResponseEntity.ok(findOrdenCabecera);
  }

  @PostMapping
  @ApiOperation(value = "Agregar una Orden Cabecera")
  public OrdenCabecera addOrdenCabecera(@RequestBody OrdenCabeceraDto rqOrdenCabecera){
    rqOrdenCabecera.setEstado(ACTIVO.getName());

    OrdenCabecera ordenCabecera = new OrdenCabecera();
    Distribuidor distribuidor = new Distribuidor();
    EstadoOrden estadoOrden = new EstadoOrden();
    Sede sede = new Sede();
    distribuidor.setIdDistribuidor(rqOrdenCabecera.getIdDistribuidor());
    estadoOrden.setIdEstadoOrden(rqOrdenCabecera.getIdEstadoOrden());
    sede.setIdSede(rqOrdenCabecera.getIdSede());

    ordenCabecera.setFechaOrdenRealizada(rqOrdenCabecera.getFechaOrdenRealizada());
    ordenCabecera.setFechaPagoRealizada(rqOrdenCabecera.getFechaPagoRealizada());
    ordenCabecera.setPrecioTotalOrdenCabecera(rqOrdenCabecera.getPrecioTotalOrdenCabecera());
    ordenCabecera.setComenarioOrdenCabecera(rqOrdenCabecera.getComenarioOrdenCabecera());
    ordenCabecera.setDistribuidor(distribuidor);
    ordenCabecera.setSede(sede);
    ordenCabecera.setEstadoOrden(estadoOrden);

    return ordenCabeceraRepository.save(ordenCabecera);
  }

  @PutMapping("/{idOrdenCabecera}")
  @ApiOperation(value = "Actualizar una Orden Cabecera")
  public ResponseEntity<OrdenCabecera> updateOrdenCabecera(@Valid @PathVariable Integer idOrdenCabecera,
                                                           @RequestBody OrdenCabeceraDto rqOrdenCabecera) throws ResourceNotFoundException{
    OrdenCabecera findOrdenCabecera = ordenCabeceraRepository.findById(idOrdenCabecera)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ningua orden cabecera con este id.")
      );

    Distribuidor distribuidor = new Distribuidor();
    EstadoOrden estadoOrden = new EstadoOrden();
    Sede sede = new Sede();
    distribuidor.setIdDistribuidor(rqOrdenCabecera.getIdDistribuidor());
    estadoOrden.setIdEstadoOrden(rqOrdenCabecera.getIdEstadoOrden());
    sede.setIdSede(rqOrdenCabecera.getIdSede());

    findOrdenCabecera.setFechaOrdenRealizada(rqOrdenCabecera.getFechaOrdenRealizada());
    findOrdenCabecera.setFechaPagoRealizada(rqOrdenCabecera.getFechaPagoRealizada());
    findOrdenCabecera.setPrecioTotalOrdenCabecera(rqOrdenCabecera.getPrecioTotalOrdenCabecera());
    findOrdenCabecera.setComenarioOrdenCabecera(rqOrdenCabecera.getComenarioOrdenCabecera());
    findOrdenCabecera.setDistribuidor(distribuidor);
    findOrdenCabecera.setSede(sede);
    findOrdenCabecera.setEstadoOrden(estadoOrden);

    final OrdenCabecera updateOrdenCabecera = ordenCabeceraRepository.save(findOrdenCabecera);
    return ResponseEntity.ok(updateOrdenCabecera);
  }

  @DeleteMapping("/{idOrdenCabecera}")
  @ApiOperation(value = "Eliminar una Orden Cabecera")
  public Map<String,Boolean> deleteOrdenCabecera(@Valid @PathVariable Integer idOrdenCabecera) throws ResourceNotFoundException{
    OrdenCabecera findOrdenCabecera = ordenCabeceraRepository.findById(idOrdenCabecera)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ninguna orden cabecera con este id.")
      );

    findOrdenCabecera.setEstado(INACTIVO.getName());
    ordenCabeceraRepository.save(findOrdenCabecera);

    Map<String,Boolean> response = new HashMap<>();
    response.put("Eliminado", Boolean.TRUE);
    return response;
  }
}
