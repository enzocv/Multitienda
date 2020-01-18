package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Pais;
import com.backend.multitienda.repositories.IPaisRepository;
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

@Api(value = "Servicio de Pais", description = "Esta API se encarga de realizar las operaciones basicas de Pais")
@RestController
@RequestMapping("/api/paises")
public class PaisController {
  @Autowired
  private IPaisRepository paisRepository;

  @GetMapping
  @ApiOperation(value = "Listar los Paises")
  public List<Pais> getAllPaises(){
    return paisRepository.findAll();
  }

  @GetMapping("/{idPais}")
  @ApiOperation(value = "Obtener un Pais")
  public ResponseEntity<Pais> getPaisById(@Valid @PathVariable Integer idPais) throws ResourceNotFoundException{
    Pais findPais = paisRepository.findById(idPais)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro nigun Pais con el id: " + idPais)
      );
    return ResponseEntity.ok(findPais);
  }
}
