package com.backend.multitienda.models.dao.permiso;

import com.backend.multitienda.models.entity.Permiso;
import org.springframework.data.repository.CrudRepository;

public interface IPermisoDao extends CrudRepository<Permiso,Integer> {
}
