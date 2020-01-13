package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.histories.PermisoHistory;
import com.backend.multitienda.histories.UsuarioHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermisoHistoryRepository extends JpaRepository<PermisoHistory, Integer> {
}
