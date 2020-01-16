package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Rol;
import com.backend.multitienda.repositories.IRolRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class PermisoController {

  @Autowired
  private IRolRepository rolRepository;

  @GetMapping
  public List<Rol> getPermisos() {
    return rolRepository.findAll();
  }

  @PostMapping
  @ApiOperation(value = "Crear un rol")
  public Rol addPermiso(@RequestBody Rol rqRol) {
    return rolRepository.save(rqRol);
  }

  @PutMapping("/{idRol}")
  @ApiOperation(value = "Actualizar rol")
  public ResponseEntity<Rol> updatePermiso(
    @Valid @PathVariable(value = "idRol") Integer idRol,
    @RequestBody Rol rqRol) throws ResourceNotFoundException {

    Rol rol = rolRepository.findById(idRol)
      .orElseThrow(() ->
        new ResourceNotFoundException("No se encontró rol con este id")
      );

    rol.setDescripcionRol(rqRol.getDescripcionRol());

    final Rol updatedRol = rolRepository.save(rol);
    return ResponseEntity.ok(updatedRol);

  }
}
