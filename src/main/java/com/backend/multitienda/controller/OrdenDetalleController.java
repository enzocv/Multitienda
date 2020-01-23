package com.backend.multitienda.controller;

import com.backend.multitienda.dto.OrdenDetalleDto;
import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.OrdenCabecera;
import com.backend.multitienda.models.entity.OrdenDetalle;
import com.backend.multitienda.models.entity.Producto;
import com.backend.multitienda.repositories.IOrdenDetalleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.function.Consumer;

import static com.backend.multitienda.models.entity.Estado.ACTIVO;
import static com.backend.multitienda.models.entity.Estado.INACTIVO;

@Api(value = "Servicio para Orden Detalle", description = "Esta API permite realizar las operaciones basicas de Orden" +
  " Detalle")
@RestController
@RequestMapping("/api/ordenesdetalles")
public class OrdenDetalleController {
  @Autowired
  private IOrdenDetalleRepository ordenDetalleRepository;

  @GetMapping("/{idOrdenDetalle}")
  @ApiOperation(value = "Obtener un Detalle de la Orden")
  public ResponseEntity<OrdenDetalle> getAllOrdenDetalles(@Valid @PathVariable Integer idOrdenDetalle) throws ResourceNotFoundException {
    OrdenDetalle findOrdenDetalle = ordenDetalleRepository.findById(idOrdenDetalle)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontor ningun Detalle de Orden con el id: " + idOrdenDetalle)
      );
    return ResponseEntity.ok(findOrdenDetalle);
  }

  @GetMapping("/{idOrdenCabecera}")
  @ApiOperation(value = "Listar los Detalles de la Orden")
  public List<OrdenDetalle> getAllOrdenesDetalle(@Valid @PathVariable Integer idOrdenCabecera) {
    return ordenDetalleRepository.findAllOrdenDetalleByIdCabecera(idOrdenCabecera);
  }

  @PostMapping
  @ApiOperation(value = "Agregar un Detalle para la Orden")
  public OrdenDetalle addOrdenDetalle(@RequestBody OrdenDetalleDto rqOrdenDetalle){

    OrdenDetalle ordenDetalle = new OrdenDetalle();
    Producto producto = new Producto();
    OrdenCabecera ordenCabecera = new OrdenCabecera();
    producto.setIdProducto(rqOrdenDetalle.getIdProducto());
    ordenCabecera.setIdOrdenCabecera(rqOrdenDetalle.getIdOrdenCabecera());

    ordenDetalle.setCantidadProducto(rqOrdenDetalle.getCantidadProducto());
    ordenDetalle.setProducto(producto);
    ordenDetalle.setIdOrdenCabecera(ordenCabecera);
    ordenDetalle.setEstado(ACTIVO.getName());

    return ordenDetalleRepository.save(ordenDetalle);
  }

  @PutMapping("/{idOrdenDetalle}")
  @ApiOperation(value = "Actualizar una Orden Detalle")
  public ResponseEntity<OrdenDetalle> updateOrdenDetalle(@Valid @PathVariable Integer idOrdenDetalle,
                                                         @RequestBody OrdenDetalleDto rqOrdenDetalle) throws ResourceNotFoundException{
    OrdenDetalle findOrdenDetalle = ordenDetalleRepository.findById(idOrdenDetalle)
    .orElseThrow(
      ()-> new ResourceNotFoundException("No se encontron ningun Detalle Orden con el id: " + idOrdenDetalle)
    );
    Producto producto = new Producto();
    OrdenCabecera ordenCabecera = new OrdenCabecera();
    producto.setIdProducto(rqOrdenDetalle.getIdProducto());
    ordenCabecera.setIdOrdenCabecera(rqOrdenDetalle.getIdOrdenCabecera());

    findOrdenDetalle.setCantidadProducto(rqOrdenDetalle.getCantidadProducto());
    findOrdenDetalle.setProducto(producto);
    findOrdenDetalle.setIdOrdenCabecera(ordenCabecera);

    final OrdenDetalle updateOrdenDetalle = ordenDetalleRepository.save(findOrdenDetalle);
    return ResponseEntity.ok(updateOrdenDetalle);
  }

  @DeleteMapping("/{idOrdenCabecera}")
  @ApiOperation(value = "Eliminar un Detalle de la Orden")
  public Map<String,Boolean> deleteOrdenDetalle(@Valid @PathVariable Integer idOrdenCabecera){
    List<OrdenDetalle> findAllOrdenesDetalle = ordenDetalleRepository.findAllOrdenDetalleByIdCabecera(idOrdenCabecera);
    findAllOrdenesDetalle.forEach(orden -> orden.setEstado(INACTIVO.getName()));
    ordenDetalleRepository.saveAll(findAllOrdenesDetalle);

    Map<String,Boolean> response = new HashMap<>();
    response.put("Eliminado",Boolean.TRUE);
    return  response;
  }

}
