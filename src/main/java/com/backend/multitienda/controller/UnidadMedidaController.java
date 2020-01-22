package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.UnidadMedida;
import com.backend.multitienda.repositories.IUnidadMedidaRepository;
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

@Api(value = "Servicio de Unidad de Medida", description = "Esta API permite realizar las operaciones basicas de la " +
  "Unidad Medida")
@RestController
@RequestMapping("/api/unidadesmedidas")
public class UnidadMedidaController {

  @Autowired
  private IUnidadMedidaRepository unidadMedidaRepository;

  @GetMapping
  @ApiOperation(value = "Listar las unidades de medida")
  public List<UnidadMedida> getAllUnidadMedidas(){
    return unidadMedidaRepository.findAll();
  }

  @GetMapping("/{idUnidadMedida}")
  @ApiOperation(value = "Obtener una Unidad de Medida")
  public ResponseEntity<UnidadMedida> getUnidadMedidaById(@Valid @PathVariable Integer idUnidadMedida) throws ResourceNotFoundException{
    UnidadMedida findUnidadMedida = unidadMedidaRepository.findById(idUnidadMedida)
      .orElseThrow(()-> new ResourceNotFoundException("No se encontro ninguna unida de medida con el id: " + idUnidadMedida)
      );
    return ResponseEntity.ok(findUnidadMedida);
  }

  @PostMapping
  @ApiOperation(value = "Agregar una unidad de medida")
  public UnidadMedida addUnidadMedida(@RequestBody UnidadMedida rqUnidadMedida){
    rqUnidadMedida.setEstado(ACTIVO.getName());
    return unidadMedidaRepository.save(rqUnidadMedida);
  }

  @PutMapping("/{idUnidadMedida}")
  @ApiOperation(value = "Actualizar una Unidad de Medida")
  public ResponseEntity<UnidadMedida> updateUnidadMedida(@Valid @PathVariable Integer idUnidadMedida,
                                                         @RequestBody UnidadMedida rqUnidadMedida) throws ResourceNotFoundException{
    UnidadMedida findUnidadMedida = unidadMedidaRepository.findById(idUnidadMedida)
      .orElseThrow(
        ()->new ResourceNotFoundException("No se encontro ninguna Unidad de Medida con este id.")
      );

    rqUnidadMedida.setIdUnidadMedida(findUnidadMedida.getIdUnidadMedida());
    final UnidadMedida updateUnidadMedida = unidadMedidaRepository.save(rqUnidadMedida);
    return ResponseEntity.ok(updateUnidadMedida);
  }

  @DeleteMapping("/{idUnidadMedida}")
  @ApiOperation(value = "Eliminar una Unidad de Medida")
  public Map<String,Boolean> deleteUnidadMedida(@Valid @PathVariable Integer idUnidadMedida) throws ResourceNotFoundException{
    UnidadMedida findUnidadMedida = unidadMedidaRepository.findById(idUnidadMedida)
      .orElseThrow(
        ()->new ResourceNotFoundException("No se encontro ninguna Unidad de Medida con este id.")
      );
    findUnidadMedida.setEstado(INACTIVO.getName());
    unidadMedidaRepository.save(findUnidadMedida);

    Map<String,Boolean> response = new HashMap<>();
    response.put("Eliminado",Boolean.TRUE);
    return response;
  }

}
