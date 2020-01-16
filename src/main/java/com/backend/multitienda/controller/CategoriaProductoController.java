package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.CategoriaProducto;
import com.backend.multitienda.repositories.ICategoriaProductoRespository;
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

@Api(value = "Servicio de Categoria Producto" , description = "Esta API permite realizar las operaciones basicas para" +
  " Categoria Producto")
@RestController
@CrossOrigin
@RequestMapping("/api/categoriaproductos")
public class CategoriaProductoController {

  @Autowired
  private ICategoriaProductoRespository categoriaProductoRespository;

  @GetMapping
  @ApiOperation(value = "Listar las categorias de productos", notes = "Listar todas las categorias de los productos")
  public List<CategoriaProducto> getAllCategoriasProducto(){
    return categoriaProductoRespository.findAll();
  }

  @GetMapping("/{idCategoriaProducto}")
  @ApiOperation(value = "Obtener una categoria producto", notes = "Obtener una categoria producto por su id")
  public ResponseEntity<CategoriaProducto> getCategoriaProductoById(
    @Valid @PathVariable int idCategoriaProducto) throws ResourceNotFoundException{
    CategoriaProducto obtenerCategoriaProducto = categoriaProductoRespository.findById(idCategoriaProducto)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro la categoria producto con el id: " + idCategoriaProducto)
      );

    return ResponseEntity.ok(obtenerCategoriaProducto);
  }

  @PostMapping
  @ApiOperation(value = "Agregar una categoria de producto")
  public CategoriaProducto addCategoriaProducto(@RequestBody CategoriaProducto rqCategoriaProducto){
    rqCategoriaProducto.setEstado(ACTIVO.getName());
    return categoriaProductoRespository.save(rqCategoriaProducto);
  }

  @PutMapping("/{idCategoriaProducto}")
  @ApiOperation(value = "Modificar una Categoria Producto")
  public ResponseEntity updateCategoriaProducto(
    @Valid @PathVariable int idCategoriaProducto,
    @RequestBody CategoriaProducto rqCategoriaProducto
  ) throws ResourceNotFoundException {
    CategoriaProducto obtenerCategoriaProducto = categoriaProductoRespository.findById(idCategoriaProducto)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro la categoria producto con este id.")
      );

    final CategoriaProducto updateCategoriaProducto = categoriaProductoRespository.save(obtenerCategoriaProducto);

    return ResponseEntity.ok(updateCategoriaProducto);
  }

  @DeleteMapping("/{idCategoriaProducto}")
  @ApiOperation(value = "Eliminar una Categoria Producto", notes = "Eliminar una Categoria Producto por su id")
  public Map<String,Boolean> deleteCategoriaProducto(
    @Valid @PathVariable Integer idCategoriaProducto) throws ResourceNotFoundException{
    CategoriaProducto obtenerCategoriaProducto = categoriaProductoRespository.findById(idCategoriaProducto)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro la categoria producto con este id.")
      );

    obtenerCategoriaProducto.setEstado(INACTIVO.getName());
    categoriaProductoRespository.save(obtenerCategoriaProducto);

    Map<String,Boolean> response = new HashMap<>();
    response.put("Eliminado",Boolean.TRUE);
    return response;
  }

}
