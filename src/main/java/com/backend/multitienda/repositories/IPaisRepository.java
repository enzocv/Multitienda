package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaisRepository extends JpaRepository<Pais,Integer> {
}
