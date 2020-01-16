package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.historiesLogs.CategoriaEmpresaHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaEmpresaHistoryRepository extends JpaRepository<CategoriaEmpresaHistory,Integer> {
}
