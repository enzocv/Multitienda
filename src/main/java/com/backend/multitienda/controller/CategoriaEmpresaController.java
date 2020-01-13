package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Categoriaempresa;
import com.backend.multitienda.models.service.categoriaempresa.ICategoriaEmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(
  value = "Servicio de categoria Empresa",
  description = "Esta API permite realizar las " +
    "operaciones básicas de las Cateogiras de las Empresas"
)
@RestController
@RequestMapping("/api/categoriaempresas")
public class CategoriaEmpresaController {
    @Autowired
    private ICategoriaEmpresaService categoriaEmpresaService;

    //region Listar
    @GetMapping
    @ApiOperation(value = "Listar categoria empresas",
                    notes = "Listar todas las categorias de las empresas")
    public List<Categoriaempresa> getAllUsuarios(){
        return categoriaEmpresaService.findAll();
    }
    //endregion


    //region Obtener por ID
    @GetMapping("/{idCategoriaEmpresa}")
    @ApiOperation(value = "Obtener una categoria",
                    notes = "Obtiene una categoria por su id")
    public ResponseEntity<Categoriaempresa> getCategoriaEmpresaById(
            @PathVariable int idCategoriaEmpresa
    ) throws ResourceNotFoundException{
        Categoriaempresa categoriaEncontrada = categoriaEmpresaService.findById(idCategoriaEmpresa).orElseThrow(
                () -> new ResourceNotFoundException(
                        "No se encontro la categoria con este ID: " + idCategoriaEmpresa)
        );

        return ResponseEntity.ok(categoriaEncontrada);
    }
    //endregion

    //region Crear
    @PostMapping
    @ApiOperation(value = "Crear una categoria para las empresas")
    public Categoriaempresa addCategoriaEmpresa(@RequestBody Categoriaempresa categoriaempresa){
        return categoriaEmpresaService.save(categoriaempresa);
    }
    //endregion

    @PutMapping
    @ApiOperation(value = "Actualizar categoria Empresa")
    public ResponseEntity updateCategoriaEmpresa(
            @RequestBody Categoriaempresa categoriaempresa
    ){
        if (!categoriaEmpresaService.existById(categoriaempresa.getIdCategoriaEmpresa()))
            return ResponseEntity.badRequest().body(
                    "No se encontro ninguna Categoria con el ID: "
                    + categoriaempresa.getIdCategoriaEmpresa());
        try {
            categoriaEmpresaService.save(categoriaempresa);
            return ResponseEntity.ok(categoriaempresa);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(
                    "No se encontro ninguna Categoria con el ID: "
                            + categoriaempresa.getIdCategoriaEmpresa());
        }
    }

    @DeleteMapping("/{idCategoriaEmpresa}")
    @ApiOperation(value = "Eliminar categoria empresa")
    public Map<String, Boolean> deleteCategoriaEmpresa(@Valid @PathVariable Integer idCategoriaEmpresa)
            throws ResourceNotFoundException{
        Categoriaempresa eliminarCategoria = categoriaEmpresaService
                                            .findById(idCategoriaEmpresa)
                                            .orElseThrow(
                                                    () -> new ResourceNotFoundException(
                                                            "No se encontro ninguna categoria con este id"
                                                    )
                                            );
        categoriaEmpresaService.delete(eliminarCategoria);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Eliminado",Boolean.TRUE);
        return response;
    }
}