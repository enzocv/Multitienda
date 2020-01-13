package com.backend.multitienda.controller;

import com.backend.multitienda.models.entity.Proveedor;
import com.backend.multitienda.models.entity.Usuario;
import com.backend.multitienda.repositories.IProveedorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "Servicio de proveedor", description = "Esta API permite realizar las operaciones básicas de los " +
  "Proveedores")
@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {


  @Autowired
  private IProveedorRepository proveedorRepository;

  @PostMapping
  @ApiOperation(value = "Crear un proveedor")
  public Proveedor addProveedor(@RequestBody Proveedor rqProveedor) {
    return proveedorRepository.save(rqProveedor);
  }

}
