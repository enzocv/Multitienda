package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.StockProducto;
import com.backend.multitienda.repositories.IStockProductoRepository;
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

@Api(value = "Servicio de Stock Producto", description = "Esta API permite realizar las operaciones basicas de Stock " +
  "Producto")
@RestController
@RequestMapping("/api/stockproductos")
public class StockProductoController {
  @Autowired
  private IStockProductoRepository stockProductoRepository;

  @GetMapping
  @ApiOperation(value = "Listar el Stock de los Productos")
  public List<StockProducto> getAllStockProductos(){
    return stockProductoRepository.findAll();
  }

  @GetMapping("/{idStockProducto}")
  @ApiOperation(value = "Obtener el Stock de un Producto")
  public ResponseEntity<StockProducto> getStockProductoById(@Valid @PathVariable Integer idStockProducto) throws ResourceNotFoundException{
    StockProducto findStockProducto = stockProductoRepository.findById(idStockProducto)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro el Stock del Producto con el id: " + idStockProducto)
      );
    return ResponseEntity.ok(findStockProducto);
  }

  @PostMapping
  @ApiOperation(value = "Agregar un stock para un Producto")
  public StockProducto addStockProducto(@RequestBody StockProducto rqStockProducto){
    rqStockProducto.setEstado(ACTIVO.getName());
    return stockProductoRepository.save(rqStockProducto);
  }

  @PutMapping("/{idStockProducto}")
  @ApiOperation(value = "Actualizar el Stock de un Producto")
  public ResponseEntity<StockProducto> updateStockProducto(@Valid @PathVariable Integer idStockProducto,
                                                           @RequestBody StockProducto rqStockProducto) throws ResourceNotFoundException{
    StockProducto findStockProducto = stockProductoRepository.findById(idStockProducto)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ningun Stock para el producto con este id.")
      );

    rqStockProducto.setIdStockProducto(findStockProducto.getIdStockProducto());

    final StockProducto updateStockProducto = stockProductoRepository.save(rqStockProducto);
    return ResponseEntity.ok(updateStockProducto);
  }

  @DeleteMapping("/{idStockProducto}")
  @ApiOperation(value = "Eliminar el stock para un producto")
  public Map<String,Boolean> deleteStockProducto(@Valid @PathVariable Integer idStockProducto) throws ResourceNotFoundException{
    StockProducto findStockProducto = stockProductoRepository.findById(idStockProducto)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ningun Stock para el producot con este id.")
      );

    findStockProducto.setEstado(INACTIVO.getName());
    stockProductoRepository.save(findStockProducto);

    Map<String,Boolean> response = new HashMap<>();
    response.put("Eliminado",Boolean.TRUE);
    return response;
  }

}
