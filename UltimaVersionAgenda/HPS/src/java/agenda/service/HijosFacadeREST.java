/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.service;

import agenda.CustomResponse;
import agenda.Hijos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author Shantal
 */
@Stateless
@Path("agenda.hijos")
public class HijosFacadeREST extends AbstractFacade<Hijos> {

    @PersistenceContext(unitName = "HPSPU")
    private EntityManager em;

    public HijosFacadeREST() {
        super(Hijos.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Hijos entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Hijos entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Hijos find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Hijos> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Hijos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @POST
    @Path("/padre/{idh}")
    //@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Hijos> findByPadre(@PathParam("idh") String idh) {
        List<Hijos> l;
        l = getEntityManager()
                    .createQuery("SELECT u FROM Hijos u WHERE  u.idPadre.idUsuario = :idh")
                    .setParameter("idh", Integer.parseInt(idh)).getResultList();
        
        
        return l;
    }
    
    public List<Map<String, Object>> enviarNotificaciones(Long idPadre) 
    {
        StringBuilder countQuery = new StringBuilder();
                countQuery.append(" select DISTINCT h.* from public.usuarios u ");
		countQuery.append(" JOIN public.hijos h on u.id_usuario = h.id_padre ");
		countQuery.append(" JOIN public.vacunas v on h.id_hijo = v.id_hijo	 ");	
		countQuery.append(" WHERE v.fecha_aplicacion <= now() + interval '2 day'  ");
		countQuery.append(" and v.notificado = false ");
		countQuery.append(" and u.id_usuario = "+idPadre.toString());
		
        List<Object[]> oResultList = em.createNativeQuery(countQuery.toString()).
                getResultList();
        if(oResultList.size()<=0){
      	  return null;
        }
        List<Map<String, Object>> resultlist = new ArrayList<>();
        for (Object[] oResultArray : oResultList) {
            Map<String, Object> oMapResult = new HashMap<>();
            oMapResult.put("idHijo", oResultArray[0]);
            oMapResult.put("IdPadre", oResultArray[1]);
            oMapResult.put("nombres", oResultArray[2]);
            oMapResult.put("apellidos", oResultArray[3]);
            oMapResult.put("edad", oResultArray[4]);
            oMapResult.put("documento", oResultArray[5]);
            //System.out.println(oResultArray[7].toString());            
            //Timestamp timestamp = (Timestamp) oResultArray[7];
            Date fecha = (Date) oResultArray[7];
            String prueba = null;   
            if(fecha != null){
                    //fecha = new Date(timestamp.getTime());
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                prueba = df.format(fecha);
            }
            oMapResult.put("fechaNacimiento", prueba);
            oMapResult.put("sexo", oResultArray[6]);
            resultlist.add(oMapResult);
        }
        
        return resultlist;
    }
    
    
    @POST
    @Path("/enviar-notificaciones")
    public Response enviarNotificacionesAp(@QueryParam("idPadre")  Long idPadre) throws Exception {
    	if(idPadre== null){
    		throw new Exception("Se requiere idPadre");
    	}

    	//CustomResponse respuesta = new CustomResponse();
    	//respuesta.setRespuesta(enviarNotificaciones(idPadre));
        //return Response.ok(respuesta).build();
        return Response.ok(enviarNotificaciones(idPadre)).build();
    }
}
