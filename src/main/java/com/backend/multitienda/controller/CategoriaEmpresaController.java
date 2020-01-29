package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.CategoriaEmpresa;
import com.backend.multitienda.models.entity.CategoriaProducto;
import com.backend.multitienda.repositories.ICategoriaEmpresaRepository;
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

@Api(value = "Servicio de categoria Empresa", description = "Esta API permite realizar las " +
  "operaciones básicas de las Cateogiras de las Empresas")
@RestController
@CrossOrigin
@RequestMapping("/api/categoriaempresas")
public class CategoriaEmpresaController {

  private final static String IMG_PATH = "src/main/java/com/backend/multitienda/img/CategoriaEmpresa/";

  @Autowired
  private ICategoriaEmpresaRepository categoriaEmpresaRepository;

  @GetMapping
  @ApiOperation(value = "Listar categoria empresas",
    notes = "Listar todas las categorias de las empresas")
  public List<CategoriaEmpresa> getAllCategoriasEmpresa() {
    return categoriaEmpresaRepository.findAll();
  }

  @GetMapping("/{idCategoriaEmpresa}")
  @ApiOperation(value = "Obtener una categoria",
    notes = "Obtiene una categoria por su id")
  public ResponseEntity<CategoriaEmpresa> getCategoriaEmpresaById(
    @PathVariable int idCategoriaEmpresa
  ) throws ResourceNotFoundException {
    CategoriaEmpresa categoriaEncontrada = categoriaEmpresaRepository.findById(idCategoriaEmpresa).orElseThrow(
      () -> new ResourceNotFoundException(
        "No se encontro la categoria con este ID: " + idCategoriaEmpresa)
    );

    return ResponseEntity.ok(categoriaEncontrada);
  }

  @PostMapping
  @ApiOperation(value = "Crear una categoria para las empresas")
  public CategoriaEmpresa addCategoriaEmpresa(@RequestBody CategoriaEmpresa rqCategoriaempresa) throws Exception {
    rqCategoriaempresa.setEstado(ACTIVO.getName());
    rqCategoriaempresa.setImagenCategoriaEmpresa(uploadImage(rqCategoriaempresa.getImagenCategoriaEmpresa(),
      rqCategoriaempresa.getDescripcionCategoriaEmpresa(), " "));

    return categoriaEmpresaRepository.save(rqCategoriaempresa);
  }

  @PutMapping
  @ApiOperation(value = "Actualizar categoria Empresa")
  public ResponseEntity<CategoriaEmpresa> updateCategoriaEmpresa(
    @Valid @PathVariable Integer idCategoriaEmpresa,
    @RequestBody CategoriaEmpresa rqCategoriaempresa) throws Exception {

    CategoriaEmpresa findCategoriaEmpresa = categoriaEmpresaRepository.findById(idCategoriaEmpresa)
      .orElseThrow(
        ()-> new ResourceNotFoundException("No se encontro ninguna Categoria Empresa con el id: " + idCategoriaEmpresa)
      );

    findCategoriaEmpresa.setImagenCategoriaEmpresa(uploadImage(rqCategoriaempresa.getImagenCategoriaEmpresa(),
      rqCategoriaempresa.getDescripcionCategoriaEmpresa(), rqCategoriaempresa.getImagenCategoriaEmpresa()));

    final CategoriaEmpresa updateCategoriaEmpresa = categoriaEmpresaRepository.save(rqCategoriaempresa);

    return ResponseEntity.ok(updateCategoriaEmpresa);
  }

  @DeleteMapping("/{idCategoriaEmpresa}")
  @ApiOperation(value = "Eliminar categoria empresa")
  public Map<String, Boolean> deleteCategoriaEmpresa(@Valid @PathVariable Integer idCategoriaEmpresa)
    throws ResourceNotFoundException {
    CategoriaEmpresa eliminarCategoria = categoriaEmpresaRepository
      .findById(idCategoriaEmpresa)
      .orElseThrow(
        () -> new ResourceNotFoundException(
          "No se encontro ninguna categoria con este id"
        )
      );
    eliminarCategoria.setEstado(INACTIVO.getName());

    categoriaEmpresaRepository.save(eliminarCategoria);
    Map<String, Boolean> response = new HashMap<>();
    response.put("Eliminado", Boolean.TRUE);
    return response;
  }

  private String uploadImage(String encodeImage, String nameImage, String oldNameImage) throws Exception {
    String image = nameImage.replace(" ","");

    String pathImage = IMG_PATH + image + ".jpg";
    File savepath = new File(pathImage);

    if (!oldNameImage.equals(" ")) {
      File oldPath = new File(oldNameImage);
      if (oldPath.exists()){
        oldPath.delete();
      }
    }

    byte[] data = Base64.getDecoder().decode(encodeImage);

    FileOutputStream fileOutputStream = new FileOutputStream(savepath);
    fileOutputStream.write(data);
    fileOutputStream.close();

    return pathImage;
  }

}