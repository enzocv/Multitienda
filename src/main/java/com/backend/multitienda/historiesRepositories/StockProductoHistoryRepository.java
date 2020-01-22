package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.historiesLogs.StockProductoHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockProductoHistoryRepository extends JpaRepository<StockProductoHistory,Integer> {
}
