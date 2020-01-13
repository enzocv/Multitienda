package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProveedorRepository extends JpaRepository<Proveedor, Integer> {
}
