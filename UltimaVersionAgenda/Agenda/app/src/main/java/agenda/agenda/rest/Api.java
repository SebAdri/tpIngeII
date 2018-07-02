package agenda.agenda.rest;


import agenda.agenda.rest.model.Hijo;
import agenda.agenda.rest.model.Usuario;
import agenda.agenda.rest.model.Vacuna;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {


    @POST("agenda.usuarios/mail/{correo}")
    Call<Usuario>  findCorreo(@Path("correo") String correo);
//    void getUsuario(Callback<List<Usuario>> callback);
    @POST("agenda.hijos/padre/{idh}")
    Call<List<Hijo>> getHijos(@Path("idh") String idPadre);

    @POST("agenda.vacunas/obtener-por-hijo")
    Call<List<Vacuna>> getVacunasHijos(@Query("idHijo") String idHijo);

    @POST("agenda.hijos/enviar-notificaciones")
    Call<List<Hijo>> enviarNotificaciones(@Query("idPadre") String idPadre);



}