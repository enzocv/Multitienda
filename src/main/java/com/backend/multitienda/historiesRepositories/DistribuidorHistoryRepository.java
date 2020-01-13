package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.historiesLogs.DistribuidorHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistribuidorHistoryRepository extends JpaRepository<DistribuidorHistory,Integer> {
}
