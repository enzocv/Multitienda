package com.backend.multitienda.controller;

import com.backend.multitienda.dto.EmpresaDto;
import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.CategoriaEmpresa;
import com.backend.multitienda.models.entity.Distrito;
import com.backend.multitienda.models.entity.Empresa;
import com.backend.multitienda.repositories.IEmpresaRepository;
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
  public Empresa addEmpresa(@RequestBody EmpresaDto rqEmpresa) {

    Empresa empresa = new Empresa();
    CategoriaEmpresa categoriaEmpresa = new CategoriaEmpresa();
    Distrito distrito = new Distrito();
    categoriaEmpresa.setIdCategoriaEmpresa(rqEmpresa.getIdCategoriaEmpresa());
    distrito.setIdDistrito(rqEmpresa.getIdDistrito());;

    empresa.setNombreEmpresa(rqEmpresa.getNombreEmpresa());
    empresa.setRucEmpresa(rqEmpresa.getRucEmpresa());
    empresa.setTelefonoEmpresa(rqEmpresa.getTelefonoEmpresa());
    empresa.setDireccionEmpresa(rqEmpresa.getDireccionEmpresa());
    empresa.setEmailEmpresa(rqEmpresa.getEmailEmpresa());
    empresa.setEstado(rqEmpresa.getEstado());
    empresa.setCategoriaEmpresa(categoriaEmpresa);
    empresa.setDistrito(distrito);
    empresa.setEstado(ACTIVO.getName());

    return empresaRepository.save(empresa);
  }

  @PutMapping("/{idEmpresa}")
  @ApiOperation(value = "Actualizar una Empresa", notes = "Actualiza una empresa registrada en la bd.")
  public ResponseEntity<Empresa> updateEmpresa(
    @Valid @PathVariable Integer idEmpresa,
    @RequestBody EmpresaDto rqEmpresa) throws ResourceNotFoundException {

    Empresa findEmpresa = empresaRepository.findById(idEmpresa)
      .orElseThrow(
        () -> new ResourceNotFoundException("No se encontro una Empresa con el id: " + idEmpresa)
      );

    CategoriaEmpresa categoriaEmpresa = new CategoriaEmpresa();
    Distrito distrito = new Distrito();
    categoriaEmpresa.setIdCategoriaEmpresa(rqEmpresa.getIdCategoriaEmpresa());
    distrito.setIdDistrito(rqEmpresa.getIdDistrito());;

    findEmpresa.setNombreEmpresa(rqEmpresa.getNombreEmpresa());
    findEmpresa.setRucEmpresa(rqEmpresa.getRucEmpresa());
    findEmpresa.setTelefonoEmpresa(rqEmpresa.getTelefonoEmpresa());
    findEmpresa.setDireccionEmpresa(rqEmpresa.getDireccionEmpresa());
    findEmpresa.setEmailEmpresa(rqEmpresa.getEmailEmpresa());
    findEmpresa.setEstado(rqEmpresa.getEstado());
    findEmpresa.setCategoriaEmpresa(categoriaEmpresa);
    findEmpresa.setDistrito(distrito);

    final Empresa updateEmpresa = empresaRepository.save(findEmpresa);

    return ResponseEntity.ok(updateEmpresa);
  }

  @DeleteMapping("/{idEmpresa}")
  @ApiOperation(value = "Eliminar una Empresa", notes = "Cambia el estado a inactivo de una Empresa")
  public Map<String, Boolean> deleteEmpresa(@Valid @PathVariable Integer idEpresa) throws ResourceNotFoundException {
    Empresa obtenerEmpresa = empresaRepository.findById(idEpresa)
      .orElseThrow(
        () -> new ResourceNotFoundException("No se encontro ninguna Empresa con este id.")
      );

    obtenerEmpresa.setEstado(INACTIVO.getName());
    empresaRepository.save(obtenerEmpresa);

    Map<String, Boolean> response = new HashMap<>();
    response.put("Eliminado", Boolean.TRUE);
    return response;
  }

}
