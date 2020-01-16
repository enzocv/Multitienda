package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Proveedor;
import com.backend.multitienda.models.entity.Usuario;
import com.backend.multitienda.repositories.IProveedorRepository;
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


@Api(value = "Servicio de proveedor", description = "Esta API permite realizar las operaciones básicas de los " +
  "Proveedores")
@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

  @Autowired
  private IProveedorRepository proveedorRepository;

  @GetMapping
  @ApiOperation(value = "Listar los Proveedores", notes = "Listar todos los Proveedores registrados en la BD")
  public List<Proveedor> getAllProveedores(){
    return proveedorRepository.findAll();
  }

  @GetMapping("/{idProveedor}")
  @ApiOperation(value = "Obtener un Proveedor", notes = "Obtener a un Proveedor por su id")
  public ResponseEntity<Proveedor> getProveedorById(
    @Valid @PathVariable Integer idProveedor) throws ResourceNotFoundException{
    Proveedor obtenerProveedor = proveedorRepository.findById(idProveedor)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ningun proveedor con el id: " + idProveedor)
      );

    return ResponseEntity.ok(obtenerProveedor);
  }

  @PostMapping
  @ApiOperation(value = "Crear un proveedor")
  public Proveedor addProveedor(@RequestBody Proveedor rqProveedor) {
    rqProveedor.setEstado(ACTIVO.getName());
    return proveedorRepository.save(rqProveedor);
  }

  @PutMapping("/{idProveedor}")
  @ApiOperation(value = "Actualizar Proveedor")
  public ResponseEntity<Proveedor> updateProveedor(
    @Valid @PathVariable Integer idProveedor,
    @RequestBody Proveedor rqProveedor) throws ResourceNotFoundException{
    Proveedor obtenerProveedor = proveedorRepository.findById(idProveedor)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro un proveedor con este id.")
      );
    final Proveedor updateProveedor = proveedorRepository.save(obtenerProveedor);
    return ResponseEntity.ok(updateProveedor);
  }

  @DeleteMapping("/{idProveedor}")
  @ApiOperation(value = "Eliminar a un Proveedor", notes = "Actualiza el estado del proveedor a Incativo")
  public Map<String,Boolean> deleteProveedor(
    @Valid @PathVariable Integer idProveedor) throws ResourceNotFoundException{
    Proveedor obtenerProveedor = proveedorRepository.findById(idProveedor)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro a ningun proveedor con este id.")
      );
    obtenerProveedor.setEstado(INACTIVO.getName());
    proveedorRepository.save(obtenerProveedor);
    Map<String,Boolean> response = new HashMap<>();
    response.put("Eliminado", Boolean.TRUE);
    return response;
  }

}
