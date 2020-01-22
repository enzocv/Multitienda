package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUnidadMedidaRepository extends JpaRepository<UnidadMedida,Integer> {
}
