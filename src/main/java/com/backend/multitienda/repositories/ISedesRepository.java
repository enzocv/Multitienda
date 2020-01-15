package com.backend.multitienda.repositories;

import com.backend.multitienda.models.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISedesRepository extends JpaRepository<Sede,Integer> {
}
