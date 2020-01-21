package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.ProductoImagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductoImagenRepository extends JpaRepository<ProductoImagen,Integer> {
  @Query(
    value = " SELECT pi.* FROM producto_imagen pi WHERE pi.id_producto = ?1",
    nativeQuery = true
  )
  List<ProductoImagen> findAllByIdProducto (Integer idProducto);
}
