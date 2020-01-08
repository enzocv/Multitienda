package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Empresa {
    private int idEmpresa;
    private String nombreEmpresa;
    private String rucEmpresa;
    private String telefonoEmpresa;
    private String direccionEmpresa;
    private String emailEmpresa;
    private Collection<Producto> productosByIdEmpresa;
    private Collection<Proveedor> proveedorsByIdEmpresa;
    private Collection<Sede> sedesByIdEmpresa;

    @Id
    @Column(name = "id_empresa", nullable = false)
    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Basic
    @Column(name = "nombre_empresa", nullable = false, length = 50)
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    @Basic
    @Column(name = "ruc_empresa", nullable = false, length = 11)
    public String getRucEmpresa() {
        return rucEmpresa;
    }

    public void setRucEmpresa(String rucEmpresa) {
        this.rucEmpresa = rucEmpresa;
    }

    @Basic
    @Column(name = "telefono_empresa", nullable = false, length = 9)
    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    @Basic
    @Column(name = "direccion_empresa", nullable = false, length = -1)
    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    @Basic
    @Column(name = "email_empresa", nullable = false, length = 50)
    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return idEmpresa == empresa.idEmpresa &&
                Objects.equals(nombreEmpresa, empresa.nombreEmpresa) &&
                Objects.equals(rucEmpresa, empresa.rucEmpresa) &&
                Objects.equals(telefonoEmpresa, empresa.telefonoEmpresa) &&
                Objects.equals(direccionEmpresa, empresa.direccionEmpresa) &&
                Objects.equals(emailEmpresa, empresa.emailEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpresa, nombreEmpresa, rucEmpresa, telefonoEmpresa, direccionEmpresa, emailEmpresa);
    }

    @OneToMany(mappedBy = "empresaByIdEmpresa")
    public Collection<Producto> getProductosByIdEmpresa() {
        return productosByIdEmpresa;
    }

    public void setProductosByIdEmpresa(Collection<Producto> productosByIdEmpresa) {
        this.productosByIdEmpresa = productosByIdEmpresa;
    }

    @OneToMany(mappedBy = "empresaByIdEmpresa")
    public Collection<Proveedor> getProveedorsByIdEmpresa() {
        return proveedorsByIdEmpresa;
    }

    public void setProveedorsByIdEmpresa(Collection<Proveedor> proveedorsByIdEmpresa) {
        this.proveedorsByIdEmpresa = proveedorsByIdEmpresa;
    }

    @OneToMany(mappedBy = "empresaByIdEmpresa")
    public Collection<Sede> getSedesByIdEmpresa() {
        return sedesByIdEmpresa;
    }

    public void setSedesByIdEmpresa(Collection<Sede> sedesByIdEmpresa) {
        this.sedesByIdEmpresa = sedesByIdEmpresa;
    }
}
