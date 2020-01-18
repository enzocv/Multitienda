package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Distrito;
import com.backend.multitienda.repositories.IDistritoRepository;
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

@Api(value = "Servicio de Distrito", description = "Esta API permite realizar las operaciones basicas de un Distrito")
@RestController
@RequestMapping("/api/distritos")
public class DistritoController {
  @Autowired
  private IDistritoRepository distritoRepository;

  @GetMapping
  @ApiOperation(value = "Listar los Distritos", notes = "Listar tdos los distritos registrados en la BD")
  public List<Distrito> getAllDistritos(){
    return distritoRepository.findAll();
  }

  @GetMapping("/{idDistrito}")
  @ApiOperation(value = "Obtener un Distrito", notes = "Obtener un Distrito por su id")
  public ResponseEntity<Distrito> getDistritoById(
    @Valid @PathVariable Integer idDIstrito) throws ResourceNotFoundException{
    Distrito obtenerDistrito = distritoRepository.findById(idDIstrito)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ningun Distrito con el id: " + idDIstrito)
      );
    return ResponseEntity.ok(obtenerDistrito);
  }
}
