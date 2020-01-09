package com.backend.multitienda.controller;

import com.backend.multitienda.models.entity.Permiso;
import com.backend.multitienda.models.service.permiso.IPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/permiso")
public class PermisoController {

    @Autowired
    private IPermisoService permisoService;

    @GetMapping("/listar")
    public List<Permiso> listar(){
        return permisoService.findAll();
    }
}
