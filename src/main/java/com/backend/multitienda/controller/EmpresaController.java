package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Empresa;
import com.backend.multitienda.models.entity.Usuario;
import com.backend.multitienda.repositories.IEmpresaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Servicio de empresa", description = "API que permite realizar las operaciones básicas de Empresas")
@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

  @Autowired
  private IEmpresaRepository empresaRepository;

  @GetMapping
  @ApiOperation(value = "Listar empresas", notes = "Lista todas las empresas")
  public List<Empresa> getAllEmpresas() {
    return empresaRepository.findAll();
  }

  @GetMapping("/{idEmpresa}")
  @ApiOperation(value = "Obtener una empresa", notes = "Obtiene un empresa por su id")
  public ResponseEntity<Empresa> getEmpresaById(
    @PathVariable(value = "idEmpresa") int idEmpresa) throws ResourceNotFoundException {

    Empresa empresa = empresaRepository.findById(idEmpresa)
      .orElseThrow(() ->
        new ResourceNotFoundException("No es encontró empresa con este id: " + idEmpresa)
      );

    return ResponseEntity.ok(empresa);
  }

  @PostMapping
  @ApiOperation(value = "Crear una empresa")
  public Empresa addEmpresa(@RequestBody Empresa rqEmpresa) {
    return empresaRepository.save(rqEmpresa);
  }



}
