package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.ProductoImagen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoImagenRepository extends JpaRepository<ProductoImagen,Integer> {
  ProductoImagen findProductoImagenByProducto_IdProducto(Integer idProducto);
}
