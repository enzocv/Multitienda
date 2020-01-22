package com.backend.multitienda.controller;


import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Sede;
import com.backend.multitienda.repositories.ISedesRepository;
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

@Api(value = "Servicio de Sede", description = "Esta API permite realizar las operaciones básicas de las " +
  "Sedes")
@RestController
@RequestMapping("/api/sedes")
public class SedeController {

  @Autowired
  private ISedesRepository sedesRepository;

  @GetMapping
  @ApiOperation(value = "Listar Sedes")
  public List<Sede> getAllSedes(){
    return sedesRepository.findAll();
  }

  @GetMapping("/{idSede}")
  @ApiOperation(value = "Obtener una Sede")
  public ResponseEntity<Sede> getSedeById(
    @Valid @PathVariable Integer idSede) throws ResourceNotFoundException{

    Sede obtenerSede = sedesRepository.findById(idSede)
      .orElseThrow(() -> new ResourceNotFoundException("No se " +
      "encontro sede con id: " + idSede));

    return ResponseEntity.ok(obtenerSede);
  }

  @PostMapping
  @ApiOperation(value = "Crear una Sede")
  public Sede addSede(@RequestBody Sede rqsede){
    rqsede.setEstado(ACTIVO.getName());
    return sedesRepository.save(rqsede);
  }

  @PutMapping("/{idSede}")
  @ApiOperation(value = "Actualizar una Sede")
  public ResponseEntity<Sede> updateSede(@Valid @PathVariable Integer idSede, @RequestBody Sede rqSede) throws ResourceNotFoundException{
    Sede findSede = sedesRepository.findById(idSede).orElseThrow(()->new ResourceNotFoundException("No se encontro " +
      "ninguna Sede con este id."));

    rqSede.setIdSede(findSede.getIdSede());
    final Sede updateSede = sedesRepository.save(rqSede);
    return ResponseEntity.ok(updateSede);
  }

  @DeleteMapping("/{idSede}")
  @ApiOperation(value = "Eliminar sede")
  public Map<String, Boolean> deleteSede(
    @PathVariable(value = "idSede") Integer idSede) throws ResourceNotFoundException {

    Sede obtenerSede = sedesRepository.findById(idSede)
      .orElseThrow(() ->
        new ResourceNotFoundException("No se encontró sede con este id")
      );

    obtenerSede.setEstado(INACTIVO.getName());
    sedesRepository.save(obtenerSede);
    Map<String, Boolean> response = new HashMap<>();
    response.put("Eliminado", Boolean.TRUE);
    return response;
  }

}
