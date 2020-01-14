package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Distribuidor;
import com.backend.multitienda.repositories.IDistribuidorRepository;
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

@Api(value = "Servicio de Distribuidor", description = "Esta API permite realizar las operaciones básicas del " +
  "Distribuidor")
@RestController
@RequestMapping("/api/distribuidores")
public class DistribuidorController {
  @Autowired
  private IDistribuidorRepository distribuidorRepository;

  @GetMapping
  @ApiOperation(value = "Listar Distribuidores", notes = "Listar todos los Distribuidores")
  public List<Distribuidor> getAllDistribuidores(){
    return distribuidorRepository.findAll();
  }

  @GetMapping("/{idDistribuidor}")
  @ApiOperation(value = "Obtener a un Distribuidor", notes = "Obtener a un Distribuidor por su Id")
  public ResponseEntity<Distribuidor> getDistribuidorById(@Valid @PathVariable int idDistribuidor) throws ResourceNotFoundException{
    Distribuidor obtenerDistribuidor = distribuidorRepository.findById(idDistribuidor)
      .orElseThrow(
        () ->
          new ResourceNotFoundException("No se encontro al Distribuidor con el siguiente id: " + idDistribuidor)
      );
    return ResponseEntity.ok(obtenerDistribuidor);
  }



}
