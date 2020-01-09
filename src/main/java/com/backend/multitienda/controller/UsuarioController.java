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
@RequestMapping("/api/usuario")
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
    @GetMapping("/listar")
    public List<Usuario> listar(){
        return usuarioService.findAdll();
    }

    /**
     * Crear a un nuevo Usuario
     * @param rqUsuario     Modelo Usuario con los datos para guardar
     * @param idPermiso     identificador de la tabla Permiso de tipo entero
     * @return  Usuario
     */
    @PostMapping("/crear/{idPermiso}")
    public void crear(@RequestBody Usuario rqUsuario, @Valid @PathVariable final Integer idPermiso){

        rqUsuario.setPermiso(convertirListaPermiso(idPermiso));

        usuarioService.save(rqUsuario);
    }

    //rqUsuario tiene el campo Permiso como null, es necesario obtenerlo de nuevo
    //o agregar uno nuevo
    @PutMapping("/modificar/{idUsuario}/permiso/{idPermiso}")
    public ResponseEntity modificar(@RequestBody Usuario rqUsuario, @Valid @PathVariable final Integer idPermiso, @Valid @PathVariable final Integer idUsuario){

        if (!validarPermisoUsuarioMoficado(rqUsuario,idUsuario,idPermiso)) {
            return ResponseEntity.badRequest().body("Id del Permiso incorrecto ...");
        }
        try{
            rqUsuario.setIdUsuario(idUsuario);

            usuarioService.save(rqUsuario);

            return ResponseEntity.ok("Usuario guardado correctamente !!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error al guardar Usuario:" + e.getMessage());
        }
    }


    /**
     * Convierte una lista del modelo Permiso
     * @param idPermiso   List<Permiso>
     * @return Permiso
     */
    private Permiso convertirListaPermiso(Integer idPermiso){
        Permiso nuevoPermiso = new Permiso();

        List<Permiso> per = permisoService.findByIdPermiso(idPermiso).stream().collect(Collectors.toList());

        for (Permiso p:
                per) {
            nuevoPermiso.setIdPermiso(p.getIdPermiso());
            nuevoPermiso.setDescripcionPermiso(p.getDescripcionPermiso());
        }
        return nuevoPermiso;
    }

    private Usuario convertirListaUsuario(Integer idUsuario){
        Usuario nuevoUsuario = new Usuario();

        List<Usuario> usu = usuarioService.findById(idUsuario).stream().collect(Collectors.toList());

        for (Usuario u:
                usu) {
            nuevoUsuario.setPermiso(u.getPermiso());
        }
        return nuevoUsuario;
    }

    /***
     * Obtiene el Permido del Usuario a modificar para comparar si es el mismo que
     * se esta enviando
     * @param usuarioModificado
     * @param idUsuario
     * @param idPermiso
     * @return boolean
     */
    private boolean validarPermisoUsuarioMoficado(Usuario usuarioModificado, Integer idUsuario, Integer idPermiso){
        try {
            Permiso permisoUsuario = convertirListaUsuario(idUsuario).getPermiso();
            if (permisoUsuario.getIdPermiso() != idPermiso){
                usuarioModificado.setPermiso(convertirListaPermiso(idPermiso));
            }
            else{ usuarioModificado.setPermiso(convertirListaPermiso(permisoUsuario.getIdPermiso())); }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
