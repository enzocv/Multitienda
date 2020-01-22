package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Pais;
import com.backend.multitienda.repositories.IPaisRepository;
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

  @PostMapping
  @ApiOperation(value = "Agregar un Pais")
  public Pais addPais(@RequestBody Pais rqPais){
    rqPais.setEstado(ACTIVO.getName());
    return paisRepository.save(rqPais);
  }

  @PutMapping("/{idPais}")
  @ApiOperation(value = "Actualizar un Pais")
  public ResponseEntity<Pais> updatePais(@Valid @PathVariable Integer idPais, @RequestBody Pais rqPais) throws ResourceNotFoundException{
    Pais findPais = paisRepository.findById(idPais).orElseThrow(()-> new ResourceNotFoundException("No se encontro ningun Pais con el id: " + idPais));
    rqPais.setIdPais(findPais.getIdPais());

    final Pais updatePais = paisRepository.save(rqPais);
    return ResponseEntity.ok(updatePais);
  }

  @DeleteMapping("/{idPais}")
  @ApiOperation(value = "Eliminar un Pais")
  public Map<String,Boolean> deletePais(@Valid @PathVariable Integer idPais) throws ResourceNotFoundException{
    Pais findPais = paisRepository.findById(idPais).orElseThrow(()->new ResourceNotFoundException("No se encontro " +
      "ningun Pais con este id."));

    findPais.setEstado(INACTIVO.getName());
    paisRepository.save(findPais);

    Map<String,Boolean> response = new HashMap<>();
    response.put("Eliminado",Boolean.TRUE);
    return response;
  }

}
