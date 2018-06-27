/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Shantal
 */
@Entity
@Table(name = "vacunas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vacunas.findAll", query = "SELECT v FROM Vacunas v")
    , @NamedQuery(name = "Vacunas.findByIdVacuna", query = "SELECT v FROM Vacunas v WHERE v.idVacuna = :idVacuna")
    , @NamedQuery(name = "Vacunas.findByNombreVacuna", query = "SELECT v FROM Vacunas v WHERE v.nombreVacuna = :nombreVacuna")
    , @NamedQuery(name = "Vacunas.findByFechaAplicacion", query = "SELECT v FROM Vacunas v WHERE v.fechaAplicacion = :fechaAplicacion")
    , @NamedQuery(name = "Vacunas.findByAplicada", query = "SELECT v FROM Vacunas v WHERE v.aplicada = :aplicada")
    , @NamedQuery(name = "Vacunas.findByNotificado", query = "SELECT v FROM Vacunas v WHERE v.notificado = :notificado")})
public class Vacunas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vacuna")
    private Integer idVacuna;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_vacuna")
    private String nombreVacuna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_aplicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAplicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplicada")
    private boolean aplicada;
    @Column(name = "notificado")
    private Boolean notificado;
    @JoinColumn(name = "id_hijo", referencedColumnName = "id_hijo")
    @ManyToOne(optional = false)
    private Hijos idHijo;

    public Vacunas() {
    }

    public Vacunas(Integer idVacuna) {
        this.idVacuna = idVacuna;
    }

    public Vacunas(Integer idVacuna, String nombreVacuna, Date fechaAplicacion, boolean aplicada) {
        this.idVacuna = idVacuna;
        this.nombreVacuna = nombreVacuna;
        this.fechaAplicacion = fechaAplicacion;
        this.aplicada = aplicada;
    }

    public Integer getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(Integer idVacuna) {
        this.idVacuna = idVacuna;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public boolean getAplicada() {
        return aplicada;
    }

    public void setAplicada(boolean aplicada) {
        this.aplicada = aplicada;
    }

    public Boolean getNotificado() {
        return notificado;
    }

    public void setNotificado(Boolean notificado) {
        this.notificado = notificado;
    }

    public Hijos getIdHijo() {
        return idHijo;
    }

    public void setIdHijo(Hijos idHijo) {
        this.idHijo = idHijo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVacuna != null ? idVacuna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacunas)) {
            return false;
        }
        Vacunas other = (Vacunas) object;
        if ((this.idVacuna == null && other.idVacuna != null) || (this.idVacuna != null && !this.idVacuna.equals(other.idVacuna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "agenda.Vacunas[ idVacuna=" + idVacuna + " ]";
    }
    
}
