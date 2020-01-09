package com.backend.multitienda.models.service.permiso;

import com.backend.multitienda.models.dao.permiso.IPermisoDao;
import com.backend.multitienda.models.entity.Permiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermisoServiceImpl implements IPermisoService {

    @Autowired
    private IPermisoDao permisoDao;

    @Override
    public List<Permiso> findAll() {
        return (List<Permiso>) permisoDao.findAll();
    }

    @Override
    public void save(Permiso permiso) {
        permisoDao.save(permiso);
    }
}
