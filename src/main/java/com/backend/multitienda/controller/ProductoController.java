package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Producto;
import com.backend.multitienda.repositories.IProductoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "Servicio para producto", description = "Esta API permite realizar las operaciones basicas para productos")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {
  @Autowired
  IProductoRepository productoRepository;

  @GetMapping
  @ApiOperation(value = "Listar los productos", notes = "Lista todos los productos registrados")
  public List<Producto> getAllProductos(){
    return productoRepository.findAll();
  }

  @GetMapping("/{idProducto}")
  @ApiOperation(value = "Obtener un producto", notes = "Obtener un producto por su ID")
  public ResponseEntity<Producto> getProductoById(@Valid @PathVariable int idProducto) throws ResourceNotFoundException{
    Producto obtenerProducto = productoRepository.findById(idProducto)
      .orElseThrow(
        () -> new ResourceNotFoundException("No se encontro ningun producto con el id: " + idProducto)
      );
    return ResponseEntity.ok(obtenerProducto);
  }

  @PostMapping
  @ApiOperation(value = "Crear un producto",notes = "Crear un producto nuevo")
  public Producto addProducto(@RequestBody Producto rqProducto){
    return productoRepository.save(rqProducto);
  }

  @PutMapping("/{idProducto}")
  @ApiOperation(value = "Actualizar Producto")
  public ResponseEntity<Producto> updateProducto(
    @Valid @PathVariable int idProducto,
    @RequestBody Producto rqProducto) throws ResourceNotFoundException {
    Producto obtenerProducto = productoRepository.findById(idProducto)
      .orElseThrow(
        () -> new ResourceNotFoundException("No se encontro el producto con este id")
      );

    final Producto modificarProducto = productoRepository.save(obtenerProducto);

    return ResponseEntity.ok(modificarProducto);
  }

  @DeleteMapping("/{idProducto}")
  @ApiOperation(value = "Eliminar Producto", notes = "Cambia el estado del producto a Inactivo")
  public Map<String,Boolean> deleteProducto(@Valid @PathVariable int idProducto)
  throws ResourceNotFoundException {
    Producto obtenerProducto = productoRepository.findById(idProducto)
      .orElseThrow(
        () -> new ResourceNotFoundException("No se encontro el producto con este id")
      );
    obtenerProducto.setEstado("I");

    final Producto eliminarProducto = productoRepository.save(obtenerProducto);

    Map<String,Boolean> response = new HashMap<>();
    response.put("Eliminado",Boolean.TRUE);
    return response;
  }
}
