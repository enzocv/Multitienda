package com.backend.multitienda.controller;

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

  private final static String IMG_PATH = "src/main/java/com/backend/multitienda/img/";

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

    Producto findProducto = productoRepository.findById(findProductoImagen.getProducto().getIdProducto())
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro el Producto.")
      );

    String nameImage = findProducto.getIdProducto() + "-" + findProducto.getNombreProducto();

    String encodeImage = encodeProductoImagen(nameImage);

    findProductoImagen.setImagenProducto(encodeImage);
    return ResponseEntity.ok(findProductoImagen);
  }


  @PostMapping
  @ApiOperation(value = "Agregar una nueva imagen para el Producto")
  public ResponseEntity addProductoImagen(@RequestBody ProductoImagen rqProductoImagen) throws Exception {
    Producto findProducto = productoRepository.findById(rqProductoImagen.getProducto().getIdProducto())
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro el Producto.")
      );

    String nameImage = findProducto.getIdProducto() + "-" + findProducto.getNombreProducto();
    uploadImage(rqProductoImagen.getImagenProducto(), nameImage.replace(" ",""));

    rqProductoImagen.setEstado(ACTIVO.getName());
    rqProductoImagen.setImagenProducto(nameImage.replace(" ","") + ".jpg");
    productoImagenRepository.save(rqProductoImagen);

    return ResponseEntity.ok(rqProductoImagen);
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

  /**
   * Convertir a Base64 la imagen del Producto
   * @param nameImage   nombre de la imagen guardada
   * @return imagen codificada
   * @throws Exception
   */
  private String encodeProductoImagen(String nameImage) throws Exception {
    File imagepath = new File(IMG_PATH + nameImage.replace(" ","") + ".jpg");
    FileInputStream imageStream = new FileInputStream(imagepath);
    byte[] data = imageStream.readAllBytes();
    String encodeImage = Base64.getEncoder().encodeToString(data);
    return encodeImage;
  }

}
