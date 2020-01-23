package com.backend.multitienda.controller;

import com.backend.multitienda.dto.DistritoDto;
import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Ciudad;
import com.backend.multitienda.models.entity.Distrito;
import com.backend.multitienda.repositories.IDistritoRepository;
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

  @PostMapping
  @ApiOperation(value = "Agregar un Distrito")
  public Distrito addDistrito(@RequestBody DistritoDto rqDistrito){

    Distrito distrito = new Distrito();
    Ciudad ciudad = new Ciudad();
    ciudad.setIdCiudad(rqDistrito.getIdCiudad());
    rqDistrito.setEstado(ACTIVO.getName());

    distrito.setNombreDistrito(rqDistrito.getNombreDistrito());
    distrito.setCiudad(ciudad);

    return distritoRepository.save(distrito);
  }

  @PutMapping("/{idDistrito}")
  @ApiOperation(value = "Actualizar un Distrito")
  public ResponseEntity<Distrito> updateDistrito(@Valid @PathVariable Integer idDistrito,
                                                 @RequestBody DistritoDto rqDistrito) throws ResourceNotFoundException{

    Distrito findDistrito = distritoRepository.findById(idDistrito)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro el distrito con el id: " + idDistrito)
      );

    Ciudad ciudad = new Ciudad();
    ciudad.setIdCiudad(rqDistrito.getIdCiudad());

    findDistrito.setNombreDistrito(rqDistrito.getNombreDistrito());
    findDistrito.setCiudad(ciudad);

    final Distrito updateDistrito = distritoRepository.save(findDistrito);
    return ResponseEntity.ok(updateDistrito);
  }

  @DeleteMapping("/{idDistrito}")
  @ApiOperation(value = "Eliminar un Distrito")
  public Map<String,Boolean> deleteDistrito(@Valid @PathVariable Integer idDistrito) throws ResourceNotFoundException{
    Distrito findDistrito = distritoRepository.findById(idDistrito)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ningun distrito con este id.")
      );

    findDistrito.setEstado(INACTIVO.getName());
    distritoRepository.save(findDistrito);

    Map<String,Boolean> response = new HashMap<>();
    response.put("Eliminado",Boolean.TRUE);
    return response;
  }

}
