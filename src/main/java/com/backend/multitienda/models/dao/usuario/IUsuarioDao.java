package com.backend.multitienda.models.dao.usuario;

import com.backend.multitienda.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, Integer> {
}
