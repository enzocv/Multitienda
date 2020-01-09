package com.backend.multitienda.controller;

import com.backend.multitienda.models.dao.permiso.IPermisoDao;
import com.backend.multitienda.models.dao.usuario.IUsuarioDao;
import com.backend.multitienda.models.entity.Usuario;
import com.backend.multitienda.models.service.permiso.IPermisoService;
import com.backend.multitienda.models.service.usuario.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/listar")
    public List<Usuario> listar(){
        return usuarioService.findAdll();
    }

    @PostMapping("/crear/{idPermiso}")
    public Usuario crear(@RequestBody Usuario rqUsuario, @PathVariable Integer idPermiso){
        return permisoDao.findById(idPermiso)
                .map(permiso -> {
                    rqUsuario.setPermiso(permiso);
                    return usuarioDao.save(rqUsuario);
                })
                .orElseThrow();
    }
}
