/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sebastian
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByPkId", query = "SELECT u FROM Users u WHERE u.pkId = :pkId")
    , @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
    , @NamedQuery(name = "Users.findByCorreo", query = "SELECT u FROM Users u WHERE u.correo = :correo")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id")
    private Integer pkId;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;

    public Users() {
    }

    public Users(Integer pkId) {
        this.pkId = pkId;
    }

    public Users(Integer pkId, String username, String correo) {
        this.pkId = pkId;
        this.username = username;
        this.correo = correo;
    }

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkId != null ? pkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.pkId == null && other.pkId != null) || (this.pkId != null && !this.pkId.equals(other.pkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "servicios.Users[ pkId=" + pkId + " ]";
    }
    
    public String Select(Integer id){
        if (id == getPkId ()){
            return getUsername() + getCorreo();
        }
        return "no encontrado";
    }
    public void Cargar (Integer id, String Correo, String nombre){
        setCorreo(Correo);
        setUsername(nombre);
        setPkId(id);
    }
    public String Modificar (Integer id, String nombre, String Correo){
        if (getPkId()==id) {
            setCorreo(Correo);
            setUsername(nombre);
            return "se ha modificado exitosamente";
        }
        return "NO SE MODIFICO";
    }
    public String Eliminar (Integer id){
        if (getPkId()==id) {
            setPkId(null);
            setCorreo(null);
            setUsername(null);
            return "se ha eliminado exitosamente";
        }
        return "NO SE ELIMINO NINGUN REGISTRO";
    }
}
