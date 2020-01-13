package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Empresa {
    private int idEmpresa;
    private String nombreEmpresa;
    private String rucEmpresa;
    private String telefonoEmpresa;
    private String direccionEmpresa;
    private String emailEmpresa;
    //private int idCategoriaEmpresa;
    private Categoriaempresa categoriaEmpresa;

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

   /* @Basic
    @Column(name = "id_categoria_empresa", nullable = false)
    public int getIdCategoriaEmpresa() {
        return idCategoriaEmpresa;
    }

    public void setIdCategoriaEmpresa(int idCategoriaEmpresa) {
        this.idCategoriaEmpresa = idCategoriaEmpresa;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return idEmpresa == empresa.idEmpresa &&
                //idCategoriaEmpresa == empresa.idCategoriaEmpresa &&
                Objects.equals(nombreEmpresa, empresa.nombreEmpresa) &&
                Objects.equals(rucEmpresa, empresa.rucEmpresa) &&
                Objects.equals(telefonoEmpresa, empresa.telefonoEmpresa) &&
                Objects.equals(direccionEmpresa, empresa.direccionEmpresa) &&
                Objects.equals(emailEmpresa, empresa.emailEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpresa, nombreEmpresa, rucEmpresa, telefonoEmpresa, direccionEmpresa); //, idCategoriaEmpresa);
    }

    @OneToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_categoria_empresa", nullable = false)
    public Categoriaempresa getCategoriaEmpresa() {
        return categoriaEmpresa;
    }

    public void setCategoriaEmpresa(Categoriaempresa categoriaempresaByIdEmpresa) {
        this.categoriaEmpresa = categoriaempresaByIdEmpresa;
    }
}
