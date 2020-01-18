package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.OrdenCabecera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenCabeceraRepository extends JpaRepository<OrdenCabecera,Integer> {
}
