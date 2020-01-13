package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.historiesLogs.PermisoHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermisoHistoryRepository extends JpaRepository<PermisoHistory, Integer> {
}
