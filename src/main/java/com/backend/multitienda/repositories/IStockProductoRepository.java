package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.StockProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStockProductoRepository extends JpaRepository<StockProducto,Integer> {
}
