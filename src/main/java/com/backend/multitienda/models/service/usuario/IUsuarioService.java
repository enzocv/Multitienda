package com.backend.multitienda.models.service.usuario;

import com.backend.multitienda.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    public List<Usuario> findAdll();

    public void save(Usuario usuario);

    public boolean existsById(Integer idUsuario);

    Optional<Usuario> findById(Integer idUsuario);
}
