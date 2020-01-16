package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.historiesLogs.ProductoHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoHistoryRepository extends JpaRepository<ProductoHistory,Integer> {
}
