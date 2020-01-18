package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.OrdenDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenDetalleRepository extends JpaRepository<OrdenDetalle,Integer> {
}
