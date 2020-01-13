package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.historiesLogs.ProveedorHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorHistoryRepository extends JpaRepository<ProveedorHistory,Integer> {
}
