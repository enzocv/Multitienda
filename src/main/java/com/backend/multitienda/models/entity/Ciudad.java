package com.backend.multitienda.models.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Ciudad {
    private int idCiudad;
    private String nombreCiudad;
    private String estado;

    private Pais paisByIdPais;

    @OneToMany(mappedBy = "ciudadByIdCiudad")
    public Collection<Distrito> getDistritosByIdCiudad() {
        return distritosByIdCiudad;
    }

    public void setDistritosByIdCiudad(Collection<Distrito> distritosByIdCiudad) {
        this.distritosByIdCiudad = distritosByIdCiudad;
    }

    private Collection<Distrito> distritosByIdCiudad;

    @ManyToOne
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais", nullable = false)
    public Pais getPaisByIdPais() {
        return paisByIdPais;
    }

    public void setPaisByIdPais(Pais paisByIdPais) {
        this.paisByIdPais = paisByIdPais;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad", nullable = false)
    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    @Basic
    @Column(name = "nombre_ciudad", nullable = false, length = 50)
    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
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
        Ciudad ciudad = (Ciudad) o;
        return idCiudad == ciudad.idCiudad &&
                Objects.equals(nombreCiudad, ciudad.nombreCiudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCiudad, nombreCiudad);
    }

    @Override
    public String toString() {
        return "Ciudad{" +
          "idCiudad=" + idCiudad +
          ", nombreCiudad='" + nombreCiudad + '\'' +
          ", estado=" + estado +
          '}';
    }
}
