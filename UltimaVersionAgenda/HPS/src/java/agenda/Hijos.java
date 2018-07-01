/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Shantal
 */
@Entity
@Table(name = "hijos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hijos.findAll", query = "SELECT h FROM Hijos h")
    , @NamedQuery(name = "Hijos.findByIdHijo", query = "SELECT h FROM Hijos h WHERE h.idHijo = :idHijo")
    , @NamedQuery(name = "Hijos.findByNombres", query = "SELECT h FROM Hijos h WHERE h.nombres = :nombres")
    , @NamedQuery(name = "Hijos.findByApellidos", query = "SELECT h FROM Hijos h WHERE h.apellidos = :apellidos")
    , @NamedQuery(name = "Hijos.findByEdad", query = "SELECT h FROM Hijos h WHERE h.edad = :edad")
    , @NamedQuery(name = "Hijos.findByDocumento", query = "SELECT h FROM Hijos h WHERE h.documento = :documento")
    , @NamedQuery(name = "Hijos.findByFechaNacimiento", query = "SELECT h FROM Hijos h WHERE h.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Hijos.findBySexo", query = "SELECT h FROM Hijos h WHERE h.sexo = :sexo")})
public class Hijos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_hijo")
    private Integer idHijo;
    @Size(max = 100)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "edad")
    private int edad;
    @Size(max = 30)
    @Column(name = "documento")
    private String documento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 3)
    @Column(name = "sexo")
    private String sexo;
    @JoinColumn(name = "id_padre", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idPadre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHijo")
    private Collection<Vacunas> vacunasCollection;

    public Hijos() {
    }

    public Hijos(Integer idHijo) {
        this.idHijo = idHijo;
    }

    public Hijos(Integer idHijo, String apellidos, int edad, Date fechaNacimiento) {
        this.idHijo = idHijo;
        this.apellidos = apellidos;
        this.edad = edad;
        String DATE_FORMAT = "dd/mm/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
         this.fechaNacimiento = sdf.parse(sdf.format(fechaNacimiento));   
        } catch (Exception e) {
        }
    }

    public Integer getIdHijo() {
        return idHijo;
    }

    public void setIdHijo(Integer idHijo) {
        this.idHijo = idHijo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getFechaNacimiento() {
        //Date date = new Date();
        // Specify the desired date format
        //String DATE_FORMAT = "dd/mm/yyyy";
        // Create object of SimpleDateFormat and pass the desired date format.
        //SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        /*
         * Use format method of SimpleDateFormat class to format the date.
         */
        //System.out.println("Today is " + sdf.format(fechaNacimiento));
        //formatter = new SimpleDateFormat("dd-MMM-yy");
//date = formatter.parse(str_date);
//        try {
//            date = sdf.parse(sdf.format(fechaNacimiento));
//        } catch (Exception e) {
//        }
String DATE_FORMAT = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
         return sdf.format(fechaNacimiento);   
        } catch (Exception e) {
        }
        return sdf.format(fechaNacimiento);
                //fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Usuarios getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Usuarios idPadre) {
        this.idPadre = idPadre;
    }

    @XmlTransient
    public Collection<Vacunas> getVacunasCollection() {
        return vacunasCollection;
    }

    public void setVacunasCollection(Collection<Vacunas> vacunasCollection) {
        this.vacunasCollection = vacunasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHijo != null ? idHijo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hijos)) {
            return false;
        }
        Hijos other = (Hijos) object;
        if ((this.idHijo == null && other.idHijo != null) || (this.idHijo != null && !this.idHijo.equals(other.idHijo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "agenda.Hijos[ idHijo=" + idHijo + " ]";
    }
    
}
