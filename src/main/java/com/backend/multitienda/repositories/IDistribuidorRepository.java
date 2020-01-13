package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.Distribuidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDistribuidorRepository extends JpaRepository<Distribuidor,Integer> {
}
