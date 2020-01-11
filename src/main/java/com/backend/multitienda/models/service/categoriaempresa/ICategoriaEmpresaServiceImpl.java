package com.backend.multitienda.models.service.categoriaempresa;

import com.backend.multitienda.models.dao.categoriaempresa.CategoriaEmpresaDao;
import com.backend.multitienda.models.entity.Categoriaempresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ICategoriaEmpresaServiceImpl implements ICategoriaEmpresaService {
    @Autowired
    private CategoriaEmpresaDao categoriaEmpresaDao;

    @Override
    public List<Categoriaempresa> findAll() {
        return categoriaEmpresaDao.findAll();
    }

    @Override
    public void save(Categoriaempresa categoriaEmpresa) {
        categoriaEmpresaDao.save(categoriaEmpresa);
    }

    @Override
    public boolean existById(Integer idCategoriaEmpresa) {
        return categoriaEmpresaDao.existsById(idCategoriaEmpresa);
    }

    @Override
    public Optional<Categoriaempresa> findById(Integer idCategoriaEmpresa) {
        return categoriaEmpresaDao.findById(idCategoriaEmpresa);
    }

    @Override
    public void deleteById(Integer idCategoriaEmpresa) {
        categoriaEmpresaDao.deleteById(idCategoriaEmpresa);
    }
}
