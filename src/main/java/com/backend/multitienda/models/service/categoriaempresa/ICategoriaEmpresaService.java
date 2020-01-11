package com.backend.multitienda.models.service.categoriaempresa;

import com.backend.multitienda.models.entity.Categoriaempresa;

import java.util.List;
import java.util.Optional;

public interface ICategoriaEmpresaService {

    public List<Categoriaempresa> findAll();
    public void save(Categoriaempresa categoriaEmpresa);
    public boolean existById(Integer idCategoriaEmpresa);
    Optional<Categoriaempresa> findById(Integer idCategoriaEmpresa);
    public void deleteById(Integer idCategoriaEmpresa);
}
