package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.OrdenDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdenDetalleRepository extends JpaRepository<OrdenDetalle,Integer> {
  List<OrdenDetalle> findByIdOrdenCabecera(Integer idOrdenCabecera);
  @Query(
    value = " SELECT od.* FROM orden_detalle od WHERE od.id_orden_cabecera = ?1",
    nativeQuery = true
  )
  List<OrdenDetalle> findAllOrdenDetalleByIdCabecera(Integer idOrdenCabecera);
}
