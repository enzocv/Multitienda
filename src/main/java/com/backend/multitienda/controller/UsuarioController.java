package com.backend.multitienda.controller;

import com.backend.multitienda.exceptions.ResourceNotFoundException;
import com.backend.multitienda.models.entity.Usuario;
import com.backend.multitienda.repositories.IUsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.backend.multitienda.models.entity.Estado.ACTIVO;
import static com.backend.multitienda.models.entity.Estado.INACTIVO;

@Api(value = "Servicio de usuario", description = "Esta API permite realizar las operaciones básicas de los " +
  "Usuarios")
@RestController //notacion para un API REST
@RequestMapping("/api/usuarios")
public class UsuarioController {

  @Autowired
  private IUsuarioRepository usuarioRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @GetMapping
  @ApiOperation(value = "Listar usuarios", notes = "Lista todos los usuarios")
  public List<Usuario> getAllUsuarios() {
    return usuarioRepository.findAll();
  }


  @GetMapping("/{idUsuario}")
  @ApiOperation(value = "Obtener un usuario", notes = "Obtiene un usuario por su id")
  public ResponseEntity<Usuario> getUsuarioById(
    @PathVariable(value = "idUsuario") int idUsuario) throws ResourceNotFoundException {

    Usuario usuario = usuarioRepository.findById(idUsuario)
      .orElseThrow(() ->
        new ResourceNotFoundException("No se encontró usuario con este id: " + idUsuario)
      );

    return ResponseEntity.ok().body(usuario);
  }

  @PostMapping
  @ApiOperation(value = "Crear un usuario")
  public Usuario addUsuario(@RequestBody Usuario rqUsuario) {
    rqUsuario.setPassword(bCryptPasswordEncoder.encode(rqUsuario.getPassword()));
    rqUsuario.setEstado(ACTIVO.getName());
    return usuarioRepository.save(rqUsuario);
  }

  @PutMapping("/{idUsuario}")
  @ApiOperation(value = "Actualizar usuario")
  public ResponseEntity<Usuario> updateUsuario(
    @Valid @PathVariable(value = "idUsuario") Integer idUsuario,
    @RequestBody Usuario rqUsuario) throws ResourceNotFoundException {

    Usuario usuario = usuarioRepository.findById(idUsuario)
      .orElseThrow(() ->
        new ResourceNotFoundException("No se encontró usuario con este id")
      );

    usuario.setEmailUsuario(rqUsuario.getEmailUsuario());
    //    usuario.setPassword(rqUsuario.getPassword());
    usuario.setPermiso(rqUsuario.getPermiso());

    final Usuario updatedUsuario = usuarioRepository.save(usuario);
    return ResponseEntity.ok(updatedUsuario);

  }


  @DeleteMapping("/{idUsuario}")
  @ApiOperation(value = "Eliminar usuario")
  public Map<String, Boolean> deleteUsuario(
    @PathVariable(value = "idUsuario") Integer idUsuario) throws ResourceNotFoundException {

    Usuario usuario = usuarioRepository.findById(idUsuario)
      .orElseThrow(() ->
        new ResourceNotFoundException("No se encontró usuario con este id")
      );

    usuario.setEstado(INACTIVO.getName());
    usuarioRepository.save(usuario);
    Map<String, Boolean> response = new HashMap<>();
    response.put("Eliminado", Boolean.TRUE);
    return response;
  }
}
