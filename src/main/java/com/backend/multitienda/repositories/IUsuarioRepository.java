package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
  Usuario findByEmailUsuario(String email);
}
