package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Ciudad;
import com.backend.multitienda.repositories.ICiudadRepository;
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

}
