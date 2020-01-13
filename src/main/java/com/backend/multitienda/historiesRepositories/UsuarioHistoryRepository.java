package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.historiesLogs.UsuarioHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioHistoryRepository extends JpaRepository<UsuarioHistory, Integer> {
}
