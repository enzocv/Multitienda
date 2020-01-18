package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Empaque;
import com.backend.multitienda.repositories.IEmpaqueRepository;
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

@Api(value = "Servicio de Empaque", description = "Esta API permite realizar las operaciones basicas para un Empaque")
@RestController
@RequestMapping("/api/empaques")
public class EmpaqueController {
  @Autowired
  private IEmpaqueRepository empaqueRepository;

  @GetMapping
  @ApiOperation(value = "Listar los Empaques", notes = "Listar todos los empaques registrados en la BD")
  public List<Empaque> getAllEmpaques(){
    return empaqueRepository.findAll();
  }

  @GetMapping("/{idEmpaque}")
  @ApiOperation(value = "Obtener a un Empaque", notes = "Obtener un Empaque por su id")
  public ResponseEntity<Empaque> getEmpaqueById(
    @Valid @PathVariable Integer idEmpaque) throws ResourceNotFoundException{
    Empaque obtenerEmpaque = empaqueRepository.findById(idEmpaque)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ningun Empaque con el id: " + idEmpaque)
      );
    return ResponseEntity.ok(obtenerEmpaque);
  }

  @PostMapping
  @ApiOperation(value = "Agregar un Empaque")
  public Empaque addEmpaque(@RequestBody Empaque rqEmpaque){
    rqEmpaque.setEstado(ACTIVO.getName());
    return empaqueRepository.save(rqEmpaque);
  }

  @PutMapping("/{idEmpaque}")
  @ApiOperation(value = "Actualizar un Empaque")
  public ResponseEntity<Empaque> updateEmpaque(
    @Valid @PathVariable Integer idEmpaque,
    @RequestBody Empaque rqEmpaque) throws ResourceNotFoundException{
    Empaque obtenerEmpaque = empaqueRepository.findById(idEmpaque)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro el Empaque con este id")
      );

    final Empaque updateEmpaque = empaqueRepository.save(obtenerEmpaque);

    return ResponseEntity.ok(updateEmpaque);
  }

  @DeleteMapping("/{idEmpaque}")
  @ApiOperation(value = "Eliminar Empaque")
  public Map<String,Boolean> deleteEmpaque(
    @Valid @PathVariable Integer idEmpaque) throws ResourceNotFoundException{
    Empaque obtenerEmpaque = empaqueRepository.findById(idEmpaque)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro el Empaque con este id")
      );

    obtenerEmpaque.setEstado(INACTIVO.getName());
    empaqueRepository.save(obtenerEmpaque);

    Map<String,Boolean> response = new HashMap<>();
    response.put("Eliminado", Boolean.TRUE);
    return response;
  }
}
