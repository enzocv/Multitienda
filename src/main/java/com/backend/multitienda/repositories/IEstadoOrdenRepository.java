package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.EstadoOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadoOrdenRepository extends JpaRepository<EstadoOrden,Integer> {
}
