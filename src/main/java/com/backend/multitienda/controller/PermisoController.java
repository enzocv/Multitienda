package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Permiso;
import com.backend.multitienda.repositories.IPermisoRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/permisos")
public class PermisoController {

    @Autowired
    private IPermisoRepository permisoRepository;

    @GetMapping
    public List<Permiso> getPermisos(){
        return permisoRepository.findAll();
    }

    @PostMapping
    @ApiOperation(value = "Crear un permiso")
    public Permiso addPermiso(@RequestBody Permiso rqPermiso) {
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
}
