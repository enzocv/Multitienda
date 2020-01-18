package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICiudadRepository extends JpaRepository<Ciudad,Integer> {
}
