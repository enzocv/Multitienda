package com.backend.multitienda.controller;

import com.backend.multitienda.models.entity.Categoriaempresa;
import com.backend.multitienda.models.service.categoriaempresa.ICategoriaEmpresaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    /**
     * Listar las categorias
     * @return List<Categoriaempresa>
     */
    @GetMapping
    public List<Categoriaempresa> listar(){
        return categoriaEmpresaService.findAll();
    }

    /**
     * Metodo para guardar categorias de empresas
     * @param categoriaempresa
     * @return Categoriaempresa
     */
    @PostMapping
    public ResponseEntity guardar(@RequestBody Categoriaempresa categoriaempresa){
        try{
            categoriaEmpresaService.save(categoriaempresa);
            return ResponseEntity.ok(categoriaempresa);
        }
        catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body("Error al guardar la categoria de la Empresa: " + e.getMessage());
        }
    }

    @PutMapping("/{idCategoriaEmpresa}")
    public ResponseEntity modificar(@RequestBody Categoriaempresa categoriaempresa){
        try{
            //categoriaempresa.setIdCategoriaEmpresa(idCategoriaEmpresa);

            categoriaEmpresaService.save(categoriaempresa);

            return ResponseEntity.ok(categoriaempresa);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(
                    "Error al modificar la Categoria de la Empresa: "
                    + e.getMessage()
            );
        }
    }

    @DeleteMapping("{idCategoriaEmpresa}")
    public void eliminar(@Valid @PathVariable Integer idCategoriaEmpresa){
        categoriaEmpresaService.deleteById(idCategoriaEmpresa);
    }
}
