package com.backend.multitienda.models.entity;

import com.backend.multitienda.audit.Auditable;
import com.backend.multitienda.listeners.EmpresaEntityListener;

import javax.persistence.*;
import java.util.Objects;

@Entity
@EntityListeners(EmpresaEntityListener.class)
public class Empresa extends Auditable<String> {
    private int idEmpresa;
    private String nombreEmpresa;
    private String rucEmpresa;
    private String telefonoEmpresa;
    private String direccionEmpresa;
    private String emailEmpresa;
    private Categoriaempresa categoriaEmpresa;
    private boolean estado;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        return Objects.hash(idEmpresa, nombreEmpresa, rucEmpresa, telefonoEmpresa, direccionEmpresa);
    }

    @ManyToOne
    @JoinColumn(name = "id_categoria_empresa", referencedColumnName = "id_categoria_empresa", nullable = false)
    public Categoriaempresa getCategoriaEmpresa() {
        return categoriaEmpresa;
    }

    public void setCategoriaEmpresa(Categoriaempresa categoriaempresaByIdEmpresa) {
        this.categoriaEmpresa = categoriaempresaByIdEmpresa;
    }

    @Override
    public String toString() {
        return "Empresa{" +
          "idEmpresa=" + idEmpresa +
          ", nombreEmpresa='" + nombreEmpresa + '\'' +
          ", rucEmpresa='" + rucEmpresa + '\'' +
          ", telefonoEmpresa='" + telefonoEmpresa + '\'' +
          ", direccionEmpresa='" + direccionEmpresa + '\'' +
          ", emailEmpresa='" + emailEmpresa + '\'' +
          ", categoriaEmpresa=" + categoriaEmpresa +
          ", estado=" + estado +
          '}';
    }
}
