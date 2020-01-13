package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.historiesLogs.SedeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SedeHistoryRepository extends JpaRepository<SedeHistory,Integer> {
}
