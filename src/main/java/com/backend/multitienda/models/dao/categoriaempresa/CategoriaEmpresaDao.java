package com.backend.multitienda.models.dao.categoriaempresa;

import com.backend.multitienda.models.entity.Categoriaempresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaEmpresaDao extends JpaRepository<Categoriaempresa,Integer> {
}
