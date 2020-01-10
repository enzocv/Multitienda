package com.backend.multitienda.models.service.usuario;

import com.backend.multitienda.models.dao.usuario.IUsuarioDao;
import com.backend.multitienda.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IUsuarioImpl implements IUsuarioService {

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

    @Override
    public boolean existsById(Integer idUsuario) {
        return usuarioDao.existsById(idUsuario);
    }

    @Override
    public Optional<Usuario> findById(Integer idUsuario) {
        return usuarioDao.findById(idUsuario);
    }

    @Override
    public void deleteById(Integer idUsuario) {
        usuarioDao.deleteById(idUsuario);
    }
}
