package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDistritoRepository extends JpaRepository<Distrito,Integer> {
}
