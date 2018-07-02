package agenda.agenda.rest.model;

import java.util.Date;


public class Usuario {
    private int idUsuario;
    private String correo, username;

    public String getPkId() {
        return String.valueOf(idUsuario);
    }

    public void setPkId(int pkId) {
        this.idUsuario = pkId;
    }

    public Usuario(String correo, String username) {
        this.correo = correo;
        this.username = username;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "pkId=" + idUsuario +
                ", correo='" + correo + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
