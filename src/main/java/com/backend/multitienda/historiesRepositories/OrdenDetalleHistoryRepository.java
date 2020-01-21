package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.historiesLogs.OrdenDetalleHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenDetalleHistoryRepository extends JpaRepository<OrdenDetalleHistory,Integer> {
}
