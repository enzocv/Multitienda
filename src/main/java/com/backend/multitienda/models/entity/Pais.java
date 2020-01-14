package com.backend.multitienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Pais {
    private int idPais;
    private String nombrePais;
    private String estado;
    
    private Collection<Sede> sedesByIdPais;
    private Collection<Ciudad> ciudadesByIdPais;
    private Collection<Empresa> empresasByIdPais;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais", nullable = false)
    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    @Basic
    @Column(name = "nombre_pais", nullable = false, length = 50)
    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    @Basic
    @Column(name = "estado", nullable = true, length = 1, columnDefinition = "CHAR")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return idPais == pais.idPais &&
                Objects.equals(nombrePais, pais.nombrePais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPais, nombrePais);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "paisByIdPais")
    public Collection<Sede> getSedesByIdPais() {
        return sedesByIdPais;
    }

    public void setSedesByIdPais(Collection<Sede> sedesByIdPais) {
        this.sedesByIdPais = sedesByIdPais;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "paisByIdPais")
    public Collection<Ciudad> getCiudadByIdPais() {
        return ciudadesByIdPais;
    }

    public void setCiudadByIdPais(Collection<Ciudad> ciudadByIdPais) {
        this.ciudadesByIdPais = ciudadByIdPais;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "paisByIdPais")
    public Collection<Empresa> getEmpresasByIdPais() {
        return empresasByIdPais;
    }

    public void setEmpresasByIdPais(Collection<Empresa> empresasByIdPais) {
        this.empresasByIdPais = empresasByIdPais;
    }

    @Override
    public String toString() {
        return "Pais{" +
          "idPais=" + idPais +
          ", nombrePais='" + nombrePais + '\'' +
          ", estado='" + estado + '\'' +
          ", sedesByIdPais=" + sedesByIdPais +
          ", ciudadesByIdPais=" + ciudadesByIdPais +
          ", empresasByIdPais=" + empresasByIdPais +
          '}';
    }
}
