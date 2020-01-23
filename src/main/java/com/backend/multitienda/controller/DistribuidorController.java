package com.backend.multitienda.controller;

import com.backend.multitienda.dto.DistribuidorDto;
import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Distribuidor;
import com.backend.multitienda.models.entity.Usuario;
import com.backend.multitienda.repositories.IDistribuidorRepository;
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

  @PostMapping
  @ApiOperation(value = "Agregar un nuevo Distribuidor")
  public Distribuidor addDistribuidor(@RequestBody DistribuidorDto rqDistribuidor){

    Distribuidor distribuidor = new Distribuidor();
    Usuario usuario = new Usuario();
    usuario.setIdUsuario(rqDistribuidor.getIdUsuario());

    distribuidor.setNombreEmpresaDistribuidor(rqDistribuidor.getNombreEmpresaDistribuidor());
    distribuidor.setNombreDistribuidor(rqDistribuidor.getNombreDistribuidor());
    distribuidor.setApellidoDistribuidor(rqDistribuidor.getApellidoDistribuidor());
    distribuidor.setDireccionDistribuidor(rqDistribuidor.getDireccionDistribuidor());
    distribuidor.setEmailDistribuidor(rqDistribuidor.getEmailDistribuidor());
    distribuidor.setRucDistribuidor(rqDistribuidor.getRucDistribuidor());
    distribuidor.setUsuario(usuario);
    distribuidor.setEstado(ACTIVO.getName());

    return distribuidorRepository.save(distribuidor);
  }

  @PutMapping("/{idDistribuidor}")
  @ApiOperation(value = "Actulizar un Distribuidor", notes = "ACtualiza un Distribuidor por su id")
  public ResponseEntity<Distribuidor> updateDistribuidor(
    @Valid @PathVariable Integer idDistribuidor,
    @RequestBody DistribuidorDto rqDistribuidor) throws ResourceNotFoundException{

    Distribuidor obtenerDistribuidor = distribuidorRepository.findById(idDistribuidor)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ningun Distribuidor con este id.")
      );

    Usuario usuario = new Usuario();
    usuario.setIdUsuario(rqDistribuidor.getIdUsuario());

    obtenerDistribuidor.setIdDistribuidor(rqDistribuidor.getIdDistribuidor());
    obtenerDistribuidor.setNombreEmpresaDistribuidor(rqDistribuidor.getNombreEmpresaDistribuidor());
    obtenerDistribuidor.setNombreDistribuidor(rqDistribuidor.getNombreDistribuidor());
    obtenerDistribuidor.setApellidoDistribuidor(rqDistribuidor.getApellidoDistribuidor());
    obtenerDistribuidor.setDireccionDistribuidor(rqDistribuidor.getDireccionDistribuidor());
    obtenerDistribuidor.setEmailDistribuidor(rqDistribuidor.getEmailDistribuidor());
    obtenerDistribuidor.setRucDistribuidor(rqDistribuidor.getRucDistribuidor());
    obtenerDistribuidor.setUsuario(usuario);

    final Distribuidor updateDistribuidor = distribuidorRepository.save(obtenerDistribuidor);

    return ResponseEntity.ok(updateDistribuidor);
  }

  @DeleteMapping("/{idDistribuidor}")
  @ApiOperation(value = "Eliminar un Distribuidor", notes = "Cambia el estado a inactivo del Distribuidor")
  public Map<String,Boolean> deleteDistribuidor(@Valid @PathVariable Integer idDistribuidor) throws ResourceNotFoundException{
    Distribuidor obtenerDistribuidor = distribuidorRepository.findById(idDistribuidor)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ningun Distribuidor con este id.")
      );

    obtenerDistribuidor.setEstado(INACTIVO.getName());
    distribuidorRepository.save(obtenerDistribuidor);

    Map<String,Boolean> response = new HashMap<>();
    response.put("Eliminado",Boolean.TRUE);
    return response;
  }
}
