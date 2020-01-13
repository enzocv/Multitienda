package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.models.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorHistoryRepository extends JpaRepository<Proveedor,Integer> {
}
