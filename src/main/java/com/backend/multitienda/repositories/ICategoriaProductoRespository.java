package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaProductoRespository extends JpaRepository<CategoriaProducto,Integer> {
}
