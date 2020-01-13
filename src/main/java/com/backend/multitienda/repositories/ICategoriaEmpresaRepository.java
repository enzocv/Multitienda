package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.Categoriaempresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaEmpresaRepository extends JpaRepository<Categoriaempresa,Integer> {
}
