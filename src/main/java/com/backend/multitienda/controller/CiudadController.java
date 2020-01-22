package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Ciudad;
import com.backend.multitienda.repositories.ICiudadRepository;
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

@Api(value = "Servicio de Ciudad", description = "Esta API permite realizar las operaciones basicas de la Ciudad")
@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {
  @Autowired
  private ICiudadRepository ciudadRepository;

  @GetMapping
  @ApiOperation(value = "Listar las Ciudades", notes = "Listar todas las Ciudades registradas en la BD")
  public List<Ciudad> getAllCiudades(){
    return ciudadRepository.findAll();
  }

  @GetMapping("/{idCiudad}")
  @ApiOperation(value = "Obtener una Ciudad", notes = "Obtener una Ciudad por su id")
  public ResponseEntity<Ciudad> getCiudadById(
    @Valid @PathVariable Integer idCiudad) throws ResourceNotFoundException{
    Ciudad obtenerCiudad = ciudadRepository.findById(idCiudad)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro la Ciudad con el id: " + idCiudad)
      );

    return ResponseEntity.ok(obtenerCiudad);
  }

  @PostMapping
  @ApiOperation(value = "Agregar una ciudad")
  public Ciudad addCiudad(@RequestBody Ciudad rqCiudad){
    rqCiudad.setEstado(ACTIVO.getName());

    return ciudadRepository.save(rqCiudad);
  }

  @PutMapping("/{idCiudad}")
  @ApiOperation(value = "Actualizar una Ciudad")
  public ResponseEntity<Ciudad> updateCiudad(@Valid @PathVariable Integer idCiudad, @RequestBody Ciudad rqCiudad) throws ResourceNotFoundException{
    Ciudad findCiudad = ciudadRepository.findById(idCiudad)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ciudad con el id: "+ idCiudad)
      );
    findCiudad.setNombreCiudad(rqCiudad.getNombreCiudad());
    findCiudad.setPais(rqCiudad.getPais());

    final Ciudad updateCiudad = ciudadRepository.save(findCiudad);

    return ResponseEntity.ok(updateCiudad);
  }

  @DeleteMapping("/{idCiudad}")
  @ApiOperation(value = "Eliminar una ciudad")
  public Map<String,Boolean> deleteCiudad(@Valid @PathVariable Integer idCiudad) throws ResourceNotFoundException{
    Ciudad findCiudad = ciudadRepository.findById(idCiudad)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro la ciudad con este id.")
      );

    findCiudad.setEstado(INACTIVO.getName());
    ciudadRepository.save(findCiudad);

    Map<String,Boolean> response = new HashMap<>();
    response.put("Eliminado",Boolean.TRUE);
    return response;
  }

}
