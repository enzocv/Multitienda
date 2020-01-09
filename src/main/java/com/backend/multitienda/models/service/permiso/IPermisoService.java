package com.backend.multitienda.models.service.permiso;

import com.backend.multitienda.models.entity.Permiso;
import com.backend.multitienda.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IPermisoService {

    public List<Permiso> findAll();

    public void save(Permiso permiso);

    Optional<Permiso> findByIdPermiso(Integer idPermiso);
}
