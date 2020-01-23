package com.backend.multitienda.controller;

import com.backend.multitienda.dto.ProductoImagenDto;
import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Producto;
import com.backend.multitienda.models.entity.ProductoImagen;
import com.backend.multitienda.repositories.IProductoImagenRepository;
import com.backend.multitienda.repositories.IProductoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.*;
import java.util.Base64;
import java.util.List;

import static com.backend.multitienda.models.entity.Estado.ACTIVO;

@Api(value = "Servicio para Producto Imagen", description = "Esta API permite realizar las operaciones basicas para " +
  "producto imagen")
@RestController
@RequestMapping("/api/productosimagenes")
public class ProductoImagenController {

  private final static String IMG_PATH = "src/main/java/com/backend/multitienda/img/ProductoImagen/";

  @Autowired
  private IProductoImagenRepository productoImagenRepository;

  @Autowired
  private IProductoRepository productoRepository;

  @GetMapping("/{idProductoImagen}")
  @ApiOperation(value = "Obtener la imagen del Producto")
  public ResponseEntity<ProductoImagen> getProductoImagenByIdProducto(@Valid @PathVariable Integer idProductoImagen) throws Exception {
    ProductoImagen findProductoImagen = productoImagenRepository.findById(idProductoImagen)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro la imagen del producto")
      );
    return ResponseEntity.ok(findProductoImagen);
  }

  @GetMapping("/producto/{idProducto}")
  @ApiOperation(value = "Obtener todas las imagenes asignadas a un producto")
  public List<ProductoImagen> getAllProductoImagenByIdProducto(@Valid @PathVariable Integer idProducto){
    return productoImagenRepository.findAllByIdProducto(idProducto);
  }

  @PostMapping
  @ApiOperation(value = "Agregar una nueva imagen para el Producto")
  public ResponseEntity addProductoImagen(@RequestBody ProductoImagenDto rqProductoImagen) throws Exception {
    Producto findProducto = productoRepository.findById(rqProductoImagen.getIdProducto())
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro el Producto.")
      );
    ProductoImagen productoImagen = new ProductoImagen();
    Producto producto = new Producto();
    producto.setIdProducto(rqProductoImagen.getIdProducto());

    String nameImage = findProducto.getIdProducto() + "_" + findProducto.getNombreProducto();
    uploadImage(rqProductoImagen.getImagenProducto(), nameImage.replace(" ",""));

    productoImagen.setImagenProducto(IMG_PATH + nameImage.replace(" ","") + ".jpg");
    productoImagen.setProducto(producto);
    productoImagen.setEstado(ACTIVO.getName());

    productoImagenRepository.save(productoImagen);

    return ResponseEntity.ok(productoImagen);
  }

  //TODO: MODIFICAR LAS IMAGENES
  @PutMapping("/{idProductoImagen}")
  @ApiOperation(value = "Actualizar Producto Imagen")
  public ResponseEntity<ProductoImagen> updateProductoImagen(@Valid @PathVariable Integer idProductoImagen,
                                                             @RequestBody ProductoImagenDto rqProductoImagen) throws ResourceNotFoundException{
    ProductoImagen findProductoImagen = productoImagenRepository.findById(idProductoImagen)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ningun Producto Imagen con el id: " + idProductoImagen)
      );

    Producto producto = new Producto();
    producto.setIdProducto(rqProductoImagen.getIdProducto());

    findProductoImagen.setProducto(producto);
    findProductoImagen.setImagenProducto(rqProductoImagen.getImagenProducto());

    final ProductoImagen updateProdudctoImagen = productoImagenRepository.save(findProductoImagen);

    return ResponseEntity.ok(updateProdudctoImagen);
  }

  /**
   * Guardar Imagen del Producto
   * @param encodeImage   imagen codificada
   * @param nameImage     nombre con el que se guardara la imagen del producto
   * @throws Exception
   */
  private void uploadImage(String encodeImage, String nameImage) throws Exception {
    File savepath = new File(IMG_PATH + nameImage.replace(" ","") + ".jpg");
    byte[] data = Base64.getDecoder().decode(encodeImage);

    FileOutputStream fileOutputStream = new FileOutputStream(savepath);
    fileOutputStream.write(data);
    fileOutputStream.close();
  }

}
