package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.OrdenCabecera;
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
  public OrdenCabecera addOrdenCabecera(@RequestBody OrdenCabecera rqOrdenCabecera){
    rqOrdenCabecera.setEstado(ACTIVO.getName());
    return ordenCabeceraRepository.save(rqOrdenCabecera);
  }

  @PutMapping("/{idOrdenCabecera}")
  @ApiOperation(value = "Actualizar una Orden Cabecera")
  public ResponseEntity<OrdenCabecera> updateOrdenCabecera(@Valid @PathVariable Integer idOrdenCabecera,
                                                           @RequestBody OrdenCabecera rqOrdenCabecera) throws ResourceNotFoundException{
    OrdenCabecera findOrdenCabecera = ordenCabeceraRepository.findById(idOrdenCabecera)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ningua orden cabecera con este id.")
      );

    rqOrdenCabecera.setIdOrdenCabecera(findOrdenCabecera.getIdOrdenCabecera());

    final OrdenCabecera updateOrdenCabecera = ordenCabeceraRepository.save(rqOrdenCabecera);
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
