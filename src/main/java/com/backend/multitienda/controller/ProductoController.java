package com.backend.multitienda.controller;

import com.backend.multitienda.dto.ProductoDto;
import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.*;
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

import static com.backend.multitienda.models.entity.Estado.ACTIVO;
import static com.backend.multitienda.models.entity.Estado.INACTIVO;

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
  public Producto addProducto(@RequestBody ProductoDto rqProducto){

    Producto producto = new Producto();
    Empaque empaque = new Empaque();
    UnidadMedida unidadMedida = new UnidadMedida();
    Empresa empresa = new Empresa();
    CategoriaProducto categoriaProducto = new CategoriaProducto();
    empaque.setIdEmpaque(rqProducto.getIdEmpaque());
    unidadMedida.setIdUnidadMedida(rqProducto.getIdUnidadMedida());
    empresa.setIdEmpresa(rqProducto.getIdEmpresa());
    categoriaProducto.setIdCategoriaProducto(rqProducto.getIdCategoriaProducto());
    rqProducto.setEstado(ACTIVO.getName());

    producto.setNombreProducto(rqProducto.getNombreProducto());
    producto.setDescripcionProducto(rqProducto.getDescripcionProducto());
    producto.setPrecioUnitario(rqProducto.getPrecioUnitario());
    producto.setPrecioEmpaque(rqProducto.getPrecioEmpaque());
    producto.setPrecioPorMayor(rqProducto.getPrecioPorMayor());
    producto.setIgvProducto(rqProducto.isIgvProducto());
    producto.setEmpaque(empaque);
    producto.setUnidadMedida(unidadMedida);
    producto.setEmpresa(empresa);
    producto.setCategoriaProducto(categoriaProducto);

    return productoRepository.save(producto);
  }

  @PutMapping("/{idProducto}")
  @ApiOperation(value = "Actualizar Producto")
  public ResponseEntity<Producto> updateProducto(
    @Valid @PathVariable int idProducto,
    @RequestBody ProductoDto rqProducto) throws ResourceNotFoundException {
    Producto findProducto = productoRepository.findById(idProducto)
      .orElseThrow(
        () -> new ResourceNotFoundException("No se encontro el producto con este id")
      );

    Empaque empaque = new Empaque();
    UnidadMedida unidadMedida = new UnidadMedida();
    Empresa empresa = new Empresa();
    CategoriaProducto categoriaProducto = new CategoriaProducto();
    empaque.setIdEmpaque(rqProducto.getIdEmpaque());
    unidadMedida.setIdUnidadMedida(rqProducto.getIdUnidadMedida());
    empresa.setIdEmpresa(rqProducto.getIdEmpresa());
    categoriaProducto.setIdCategoriaProducto(rqProducto.getIdCategoriaProducto());

    findProducto.setNombreProducto(rqProducto.getNombreProducto());
    findProducto.setDescripcionProducto(rqProducto.getDescripcionProducto());
    findProducto.setPrecioUnitario(rqProducto.getPrecioUnitario());
    findProducto.setPrecioEmpaque(rqProducto.getPrecioEmpaque());
    findProducto.setPrecioPorMayor(rqProducto.getPrecioPorMayor());
    findProducto.setIgvProducto(rqProducto.isIgvProducto());
    findProducto.setEmpaque(empaque);
    findProducto.setUnidadMedida(unidadMedida);
    findProducto.setEmpresa(empresa);
    findProducto.setCategoriaProducto(categoriaProducto);

    final Producto updateProducto = productoRepository.save(findProducto);

    return ResponseEntity.ok(updateProducto);
  }

  @DeleteMapping("/{idProducto}")
  @ApiOperation(value = "Eliminar Producto", notes = "Cambia el estado del producto a Inactivo")
  public Map<String,Boolean> deleteProducto(@Valid @PathVariable int idProducto)
  throws ResourceNotFoundException {
    Producto obtenerProducto = productoRepository.findById(idProducto)
      .orElseThrow(
        () -> new ResourceNotFoundException("No se encontro el producto con este id")
      );
    obtenerProducto.setEstado(INACTIVO.getName());

    productoRepository.save(obtenerProducto);

    Map<String,Boolean> response = new HashMap<>();
    response.put("Eliminado",Boolean.TRUE);
    return response;
  }
}
