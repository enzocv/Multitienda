package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Proveedor {
    private int idProveedor;
    private String nombreProveedor;
    private String apellidoProveedor;
    private String rucProveedor;
    private int idUsuario;
    private int idEmpresa;
    private Usuario usuarioByIdUsuario;
    private Empresa empresaByIdEmpresa;

    @Id
    @Column(name = "id_proveedor", nullable = false)
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Basic
    @Column(name = "nombre_proveedor", nullable = false, length = 50)
    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    @Basic
    @Column(name = "apellido_proveedor", nullable = false, length = 50)
    public String getApellidoProveedor() {
        return apellidoProveedor;
    }

    public void setApellidoProveedor(String apellidoProveedor) {
        this.apellidoProveedor = apellidoProveedor;
    }

    @Basic
    @Column(name = "ruc_proveedor", nullable = false, length = 11)
    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }

    @Basic
    @Column(name = "id_usuario", nullable = false)
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Basic
    @Column(name = "id_empresa", nullable = false)
    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proveedor proveedor = (Proveedor) o;
        return idProveedor == proveedor.idProveedor &&
                idUsuario == proveedor.idUsuario &&
                idEmpresa == proveedor.idEmpresa &&
                Objects.equals(nombreProveedor, proveedor.nombreProveedor) &&
                Objects.equals(apellidoProveedor, proveedor.apellidoProveedor) &&
                Objects.equals(rucProveedor, proveedor.rucProveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProveedor, nombreProveedor, apellidoProveedor, rucProveedor, idUsuario, idEmpresa);
    }

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    public Usuario getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(Usuario usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }

    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false)
    public Empresa getEmpresaByIdEmpresa() {
        return empresaByIdEmpresa;
    }

    public void setEmpresaByIdEmpresa(Empresa empresaByIdEmpresa) {
        this.empresaByIdEmpresa = empresaByIdEmpresa;
    }
}
