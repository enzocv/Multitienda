package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermisoRepository extends JpaRepository<Permiso,Integer> {
}
