package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.CategoriaProducto;
import com.backend.multitienda.repositories.ICategoriaProductoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.backend.multitienda.models.entity.Estado.ACTIVO;
import static com.backend.multitienda.models.entity.Estado.INACTIVO;

@Api(value = "Servicio de Categoria Producto", description = "Esta API permite realizar las operaciones basicas para" +
  " Categoria Producto")
@RestController
@CrossOrigin
@RequestMapping("/api/categoriaproductos")
public class CategoriaProductoController {

  private final static String IMG_PATH = "src/main/java/com/backend/multitienda/img/CategoriaProducto/";

  @Autowired
  private ICategoriaProductoRepository categoriaProductoRespository;

  @GetMapping
  @ApiOperation(value = "Listar las categorias de productos", notes = "Listar todas las categorias de los productos")
  public List<CategoriaProducto> getAllCategoriasProducto() {
    return categoriaProductoRespository.findAll();
  }

  @GetMapping("/{idCategoriaProducto}")
  @ApiOperation(value = "Obtener una categoria producto", notes = "Obtener una categoria producto por su id")
  public ResponseEntity<CategoriaProducto> getCategoriaProductoById(
    @Valid @PathVariable int idCategoriaProducto) throws ResourceNotFoundException {
    CategoriaProducto obtenerCategoriaProducto = categoriaProductoRespository.findById(idCategoriaProducto)
      .orElseThrow(
        () -> new ResourceNotFoundException("No se encontro la categoria producto con el id: " + idCategoriaProducto)
      );

    return ResponseEntity.ok(obtenerCategoriaProducto);
  }

  @PostMapping
  @ApiOperation(value = "Agregar una categoria de producto")
  public CategoriaProducto addCategoriaProducto(@RequestBody CategoriaProducto rqCategoriaProducto) throws Exception {
    rqCategoriaProducto.setEstado(ACTIVO.getName());

    rqCategoriaProducto.setImagenCategoriaProducto(uploadImage(rqCategoriaProducto.getImagenCategoriaProducto(),
      rqCategoriaProducto.getNombreCategoriaProducto()));

    return categoriaProductoRespository.save(rqCategoriaProducto);
  }

  @PutMapping("/{idCategoriaProducto}")
  @ApiOperation(value = "Modificar una Categoria Producto")
  public ResponseEntity<CategoriaProducto> updateCategoriaProducto(
    @Valid @PathVariable int idCategoriaProducto,
    @RequestBody CategoriaProducto rqCategoriaProducto) throws ResourceNotFoundException {

    CategoriaProducto findCategoriaProducto = categoriaProductoRespository.findById(idCategoriaProducto)
      .orElseThrow(
        () -> new ResourceNotFoundException("No se encontro la categoria producto con este id.")
      );

    rqCategoriaProducto.setIdCategoriaProducto(findCategoriaProducto.getIdCategoriaProducto());

    final CategoriaProducto updateCategoriaProducto = categoriaProductoRespository.save(rqCategoriaProducto);

    return ResponseEntity.ok(updateCategoriaProducto);
  }

  @DeleteMapping("/{idCategoriaProducto}")
  @ApiOperation(value = "Eliminar una Categoria Producto", notes = "Eliminar una Categoria Producto por su id")
  public Map<String, Boolean> deleteCategoriaProducto(
    @Valid @PathVariable Integer idCategoriaProducto) throws ResourceNotFoundException {
    CategoriaProducto obtenerCategoriaProducto = categoriaProductoRespository.findById(idCategoriaProducto)
      .orElseThrow(
        () -> new ResourceNotFoundException("No se encontro la categoria producto con este id.")
      );

    obtenerCategoriaProducto.setEstado(INACTIVO.getName());
    categoriaProductoRespository.save(obtenerCategoriaProducto);

    Map<String, Boolean> response = new HashMap<>();
    response.put("Eliminado", Boolean.TRUE);
    return response;
  }

  private String uploadImage(String encodeImage, String nameImage) throws Exception {
    String image = nameImage.replace(" ","");

    String pathImage = IMG_PATH + image + ".jpg";
    File savepath = new File(pathImage);
    byte[] data = Base64.getDecoder().decode(encodeImage);

    FileOutputStream fileOutputStream = new FileOutputStream(savepath);
    fileOutputStream.write(data);
    fileOutputStream.close();

    return pathImage;
  }

}
