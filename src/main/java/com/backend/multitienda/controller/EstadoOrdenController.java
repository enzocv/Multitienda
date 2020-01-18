package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.EstadoOrden;
import com.backend.multitienda.repositories.IEstadoOrdenRepository;
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

@Api(value = "Servicio de Estado Orden", description = "Espa API permite realizar las operaciones basicas de Estado " +
  "Orden")
@RestController
@RequestMapping("/api/estadoordenes")
public class EstadoOrdenController {
  @Autowired
  private IEstadoOrdenRepository estadoOrdenRespository;

  @GetMapping
  @ApiOperation(value = "Listar los Estado de Orden", notes = "Listar todos los estados de las Ordenes registrados en" +
    " la bd")
  public List<EstadoOrden> getAllEstadoOrdenes(){
    return estadoOrdenRespository.findAll();
  }

  @GetMapping("/{idEstadoOrden}")
  @ApiOperation(value = "Obtener un Estado Orden", notes = "Obtener el estado de la orden por su id")
  public ResponseEntity<EstadoOrden> getEstadoOrdenById(@Valid @PathVariable Integer idEstadoOrden) throws ResourceNotFoundException{
    EstadoOrden findEstadoOrden = estadoOrdenRespository.findById(idEstadoOrden)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ningun Estado Orden con el id: " + idEstadoOrden)
      );
    return ResponseEntity.ok(findEstadoOrden);
  }

  @PostMapping
  @ApiOperation(value = "Agregar un Estado Orden")
  public EstadoOrden addEstadoOrden(@RequestBody EstadoOrden rqEstadoOrden){
    rqEstadoOrden.setEstado(ACTIVO.getName());
    return estadoOrdenRespository.save(rqEstadoOrden);
  }

  @PutMapping("/{idEstadoOrden}")
  @ApiOperation(value = "Actualizar un Estado Orden")
  public ResponseEntity<EstadoOrden> updateEstadoOrden(@Valid @PathVariable Integer idEstadoOrden,
                                                       @RequestBody EstadoOrden rqEstadoOrden) throws ResourceNotFoundException{
    EstadoOrden findEstadoOrden = estadoOrdenRespository.findById(idEstadoOrden)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ningun Estado Orden con este id.")
      );

    final EstadoOrden updateEstadoOrden = estadoOrdenRespository.save(findEstadoOrden);
    return ResponseEntity.ok(updateEstadoOrden);
  }

  @DeleteMapping("/{idEstadoOrden}")
  @ApiOperation(value = "Eliminar un Estado Orden")
  public Map<String,Boolean> deleteEstadoOrden(@Valid @PathVariable Integer idEstadoOrden) throws ResourceNotFoundException{
    EstadoOrden findEstadoOrden = estadoOrdenRespository.findById(idEstadoOrden)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ningun Estado Orden con este id.")
      );

    findEstadoOrden.setEstado(INACTIVO.getName());
    estadoOrdenRespository.save(findEstadoOrden);

    Map<String,Boolean> response = new HashMap<>();
    response.put("Eliminado", Boolean.TRUE);
    return response;
  }
}
