package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.historiesLogs.CategoriaProductoHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaProdcutoHistoryRepository extends JpaRepository<CategoriaProductoHistory,Integer> {
}
