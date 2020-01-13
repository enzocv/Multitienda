package com.backend.multitienda.historiesRepositories;

import com.backend.multitienda.histories.UsuarioHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioHistoryRepository extends JpaRepository<UsuarioHistory, Integer> {
}
