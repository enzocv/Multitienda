package com.backend.multitienda.models.entity;

import com.backend.multitienda.audit.Auditable;
import com.backend.multitienda.listeners.DistribuidorEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@EntityListeners(DistribuidorEntityListener.class)
public class Distribuidor extends Auditable<String> {
    private int idDistribuidor;
    private String nombreEmpresaDistribuidor;
    private String nombreDistribuidor;
    private String apellidoDistribuidor;
    private String direccionDistribuidor;
    private String emailDistribuidor;
    private String rucDistribuidor;
    private Usuario usuarioByIdUsuario;

    @JsonIgnore
    private Collection<Ordencabecera> ordencabecerasByIdDistribuidor;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_distribuidor", nullable = false)
    public int getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(int idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    @Basic
    @Column(name = "nombre_empresa_distribuidor", nullable = false, length = 50)
    public String getNombreEmpresaDistribuidor() {
        return nombreEmpresaDistribuidor;
    }

    public void setNombreEmpresaDistribuidor(String nombreEmpresaDistribuidor) {
        this.nombreEmpresaDistribuidor = nombreEmpresaDistribuidor;
    }

    @Basic
    @Column(name = "nombre_distribuidor", nullable = false, length = 50)
    public String getNombreDistribuidor() {
        return nombreDistribuidor;
    }

    public void setNombreDistribuidor(String nombreDistribuidor) {
        this.nombreDistribuidor = nombreDistribuidor;
    }

    @Basic
    @Column(name = "apellido_distribuidor", nullable = false, length = 50)
    public String getApellidoDistribuidor() {
        return apellidoDistribuidor;
    }

    public void setApellidoDistribuidor(String apellidoDistribuidor) {
        this.apellidoDistribuidor = apellidoDistribuidor;
    }

    @Basic
    @Column(name = "direccion_distribuidor", nullable = false, length = -1)
    public String getDireccionDistribuidor() {
        return direccionDistribuidor;
    }

    public void setDireccionDistribuidor(String direccionDistribuidor) {
        this.direccionDistribuidor = direccionDistribuidor;
    }

    @Basic
    @Column(name = "email_distribuidor", nullable = false, length = 50)
    public String getEmailDistribuidor() {
        return emailDistribuidor;
    }

    public void setEmailDistribuidor(String emailDistribuidor) {
        this.emailDistribuidor = emailDistribuidor;
    }

    @Basic
    @Column(name = "ruc_distribuidor", nullable = false, length = 11)
    public String getRucDistribuidor() {
        return rucDistribuidor;
    }

    public void setRucDistribuidor(String rucDistribuidor) {
        this.rucDistribuidor = rucDistribuidor;
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distribuidor that = (Distribuidor) o;
        return idDistribuidor == that.idDistribuidor &&
                Objects.equals(nombreEmpresaDistribuidor, that.nombreEmpresaDistribuidor) &&
                Objects.equals(nombreDistribuidor, that.nombreDistribuidor) &&
                Objects.equals(apellidoDistribuidor, that.apellidoDistribuidor) &&
                Objects.equals(direccionDistribuidor, that.direccionDistribuidor) &&
                Objects.equals(emailDistribuidor, that.emailDistribuidor) &&
                Objects.equals(rucDistribuidor, that.rucDistribuidor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDistribuidor, nombreEmpresaDistribuidor, nombreDistribuidor, apellidoDistribuidor, direccionDistribuidor, emailDistribuidor, rucDistribuidor);
    }

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    public Usuario getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(Usuario usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }

    @OneToMany(mappedBy = "distribuidorByIdDistribuidor")
    public Collection<Ordencabecera> getOrdencabecerasByIdDistribuidor() {
        return ordencabecerasByIdDistribuidor;
    }

    public void setOrdencabecerasByIdDistribuidor(Collection<Ordencabecera> ordencabecerasByIdDistribuidor) {
        this.ordencabecerasByIdDistribuidor = ordencabecerasByIdDistribuidor;
    }

    @Override
    public String toString() {
        return "Distribuidor{" +
                "idDistribuidor=" + idDistribuidor +
                ", nombreEmpresaDistribuidor='" + nombreEmpresaDistribuidor + '\'' +
                ", nombreDistribuidor='" + nombreDistribuidor + '\'' +
                ", apellidoDistribuidor='" + apellidoDistribuidor + '\'' +
                ", direccionDistribuidor='" + direccionDistribuidor + '\'' +
                ", emailDistribuidor='" + emailDistribuidor + '\'' +
                ", rucDistribuidor='" + rucDistribuidor + '\'' +
                ", usuarioByIdUsuario=" + usuarioByIdUsuario +
                ", ordencabecerasByIdDistribuidor=" + ordencabecerasByIdDistribuidor +
                '}';
    }
}
