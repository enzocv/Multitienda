package com.backend.multitienda.controller;

import com.backend.multitienda.models.dao.permiso.IPermisoDao;
import com.backend.multitienda.models.dao.usuario.IUsuarioDao;
import com.backend.multitienda.models.entity.Permiso;
import com.backend.multitienda.models.entity.Usuario;
import com.backend.multitienda.models.service.permiso.IPermisoService;
import com.backend.multitienda.models.service.usuario.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController //notacion para un API REST
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private IPermisoDao permisoDao;

    @Autowired
    private IPermisoService permisoService;

    @Autowired
    private IUsuarioService usuarioService;

    /**
     * Listar a todos los Usuarios
     * @return List<Usuario>
     */
    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.findAdll();
    }

    /**
     * Crear a un nuevo Usuario
     * @param rqUsuario     Modelo Usuario con los datos para guardar
     * @param //idPermiso     identificador de la tabla Permiso de tipo entero
     * @return  Usuario
     */
    @PostMapping("/{idPermiso}")
    public ResponseEntity crear(@RequestBody Usuario rqUsuario,
                                @Valid @PathVariable final Integer idPermiso){

        try{
            rqUsuario.setPermiso(permisoService.findByIdPermiso(idPermiso).get());

            usuarioService.save(rqUsuario);

            return ResponseEntity.ok(rqUsuario);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error al guardar Usuario:  " + e.getMessage());
        }
    }

    //rqUsuario tiene el campo Permiso como null, es necesario obtenerlo de nuevo
    //o agregar uno nuevo
    @PutMapping("/{idUsuario}/permiso/{idPermiso}")
    public ResponseEntity modificar(@RequestBody Usuario rqUsuario, @Valid @PathVariable final Integer idPermiso, @Valid @PathVariable final Integer idUsuario){
        try{
            rqUsuario.setIdUsuario(idUsuario);

            rqUsuario.setPermiso(permisoService.findByIdPermiso(idPermiso).get());

            usuarioService.save(rqUsuario);

            return ResponseEntity.ok(rqUsuario);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error al guardar Usuario:  " + e.getMessage());
        }
    }

    @DeleteMapping("/{idUsuario}")
    public void eliminar(@PathVariable Integer idUsuario){
        usuarioService.deleteById(idUsuario);
    }

}
