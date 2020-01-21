package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.OrdenDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdenDetalleRepository extends JpaRepository<OrdenDetalle,Integer> {
  List<OrdenDetalle> findByIdOrdenCabecera(Integer idOrdenCabecera);
}
