package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.historiesLogs.EmpresaHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaHistoryRepository extends JpaRepository<EmpresaHistory,Integer> {
}
