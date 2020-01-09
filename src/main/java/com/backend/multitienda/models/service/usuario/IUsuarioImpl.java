package com.backend.multitienda.models.service.usuario;

import com.backend.multitienda.models.dao.permiso.IPermisoDao;
import com.backend.multitienda.models.dao.usuario.IUsuarioDao;
import com.backend.multitienda.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUsuarioImpl implements IUsuarioService{

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public List<Usuario> findAdll() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    public void save(Usuario usuario) {
        usuarioDao.save(usuario);
    }
}
