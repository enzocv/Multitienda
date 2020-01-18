package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Permiso;
import com.backend.multitienda.repositories.IPermisoRepository;
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

@Api(value = "Servicio de permiso", description = "Esta API permite realizar las " +
  "operaciones básicas de los Permisos para los usuario")
@RestController
@CrossOrigin
@RequestMapping("/api/permisos")
public class PermisoController {

    @Autowired
    private IPermisoRepository permisoRepository;

    @GetMapping
    @ApiOperation(value = "Listar los Permisos", notes = "Listar todos los Permisos registrados en la BD")
    public List<Permiso> getAllPermisos(){
        return permisoRepository.findAll();
    }

    @GetMapping("/{idPermiso}")
    @ApiOperation(value = "Obtener un Permiso", notes = "Obtener un Permiso por su id")
    public ResponseEntity<Permiso> getPermisoById(
      @Valid @PathVariable Integer idPermiso) throws ResourceNotFoundException{
        Permiso obtenerPermiso = permisoRepository.findById(idPermiso)
          .orElseThrow(
            ()-> new ResourceNotFoundException("No se encontro ningun Permiso con el id: " + idPermiso)
          );

        return ResponseEntity.ok(obtenerPermiso);
    }

    @PostMapping
    @ApiOperation(value = "Crear un permiso")
    public Permiso addPermiso(@RequestBody Permiso rqPermiso) {
        rqPermiso.setEstado(ACTIVO.getName());
        return permisoRepository.save(rqPermiso);
    }

    @PutMapping("/{idPermiso}")
    @ApiOperation(value = "Actualizar permiso")
    public ResponseEntity<Permiso> updatePermiso(
      @Valid @PathVariable(value = "idPermiso") Integer idPermiso,
      @RequestBody Permiso rqPermiso) throws ResourceNotFoundException {

        Permiso permiso = permisoRepository.findById(idPermiso)
          .orElseThrow(() ->
            new ResourceNotFoundException("No se encontró permiso con este id")
          );

        permiso.setDescripcionPermiso(rqPermiso.getDescripcionPermiso());

        final Permiso updatedPermiso = permisoRepository.save(permiso);
        return ResponseEntity.ok(updatedPermiso);

    }

    @DeleteMapping("/{idPermiso}")
    @ApiOperation(value = "Eliminar un Permiso")
    public Map<String,Boolean> deletePermiso(
      @Valid @PathVariable Integer idPermiso) throws ResourceNotFoundException{
        Permiso obtenerPermiso = permisoRepository.findById(idPermiso)
          .orElseThrow(
            ()-> new ResourceNotFoundException("No se encontro ningun Permiso con este id.")
          );

        obtenerPermiso.setEstado(INACTIVO.getName());
        permisoRepository.save(obtenerPermiso);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        return response;

    }
}
