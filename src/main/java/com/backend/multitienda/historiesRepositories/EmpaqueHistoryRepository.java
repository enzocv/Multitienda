package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.historiesLogs.EmpaqueHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpaqueHistoryRepository extends JpaRepository<EmpaqueHistory,Integer> {
}
