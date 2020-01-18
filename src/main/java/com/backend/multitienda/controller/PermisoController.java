package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Rol;
import com.backend.multitienda.repositories.IRolRepository;
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
    private IRolRepository rolRepository;

    @GetMapping
    @ApiOperation(value = "Listar los Roles", notes = "Listar todos los Roles registrados en la BD")
    public List<Rol> getAllRoles(){
        return rolRepository.findAll();
    }

    @GetMapping("/{idRol}")
    @ApiOperation(value = "Obtener un Rol", notes = "Obtener un Rol por su id")
    public ResponseEntity<Rol> getRolById(
      @Valid @PathVariable Integer idRol) throws ResourceNotFoundException{
        Rol obtenerRol = rolRepository.findById(idRol)
          .orElseThrow(
            ()-> new ResourceNotFoundException("No se encontro ningun Rol con el id: " + idRol)
          );

        return ResponseEntity.ok(obtenerRol);
    }

    @PostMapping
    @ApiOperation(value = "Crear un rol")
    public Rol addRol(@RequestBody Rol rqRol) {
        rqRol.setEstado(ACTIVO.getName());
        return rolRepository.save(rqRol);
    }

    @PutMapping("/{idRol}")
    @ApiOperation(value = "Actualizar rol")
    public ResponseEntity<Rol> updateRol(
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

    @DeleteMapping("/{idRol}")
    @ApiOperation(value = "Eliminar un Permiso")
    public Map<String,Boolean> deletePermiso(
      @Valid @PathVariable Integer idRol) throws ResourceNotFoundException{
        Rol obtenerRol = rolRepository.findById(idRol)
          .orElseThrow(
            ()-> new ResourceNotFoundException("No se encontro ningun Rol con este id.")
          );

        obtenerRol.setEstado(INACTIVO.getName());
        rolRepository.save(obtenerRol);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        return response;

    }
}
