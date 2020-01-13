package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Proveedor {
    private int idProveedor;
    private String nombreProveedor;
    private String apellidoProveedor;
    private String rucProveedor;
    private Usuario usuario;
    private Empresa empresa;
    private boolean estado;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "estado", nullable = true, length = 1, columnDefinition = "BIT")
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proveedor proveedor = (Proveedor) o;
        return idProveedor == proveedor.idProveedor &&
                Objects.equals(nombreProveedor, proveedor.nombreProveedor) &&
                Objects.equals(apellidoProveedor, proveedor.apellidoProveedor) &&
                Objects.equals(rucProveedor, proveedor.rucProveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProveedor, nombreProveedor, apellidoProveedor, rucProveedor);
    }

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuarioByIdUsuario) {
        this.usuario = usuarioByIdUsuario;
    }

    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false)
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresaByIdEmpresa) {
        this.empresa = empresaByIdEmpresa;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
          "idProveedor=" + idProveedor +
          ", nombreProveedor='" + nombreProveedor + '\'' +
          ", apellidoProveedor='" + apellidoProveedor + '\'' +
          ", rucProveedor='" + rucProveedor + '\'' +
          ", usuario=" + usuario +
          ", empresa=" + empresa +
          ", estado=" + estado +
          '}';
    }
}
