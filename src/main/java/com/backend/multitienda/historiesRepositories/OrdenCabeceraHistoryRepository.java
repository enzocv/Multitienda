package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.historiesLogs.OrdenCabeceraHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenCabeceraHistoryRepository  extends JpaRepository<OrdenCabeceraHistory, Integer> {
}
