/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.service;

import agenda.Usuarios;
import agenda.Vacunas;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shantal
 */
@Stateless
@Path("agenda.vacunas")
public class VacunasFacadeREST extends AbstractFacade<Vacunas> {
   
    @PersistenceContext(unitName = "HPSPU")
    private EntityManager em;

    public VacunasFacadeREST() {
        super(Vacunas.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Vacunas entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Vacunas entity) {
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
    public Vacunas find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Vacunas> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Vacunas> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    /*
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
    */
    /*
    @POST
    @Path("/obtener-por-hijo/{idHijo}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Vacunas> getVacunas(@PathParam("idHijo") String idHijo) {
        List<Vacunas> v;
        try {
            v = getEntityManager()
                    .createQuery("SELECT u FROM Vacunas u WHERE u.idHijo.idHijo = :idHijo")
                    .setParameter("idHijo", idHijo).getResultList();
            return v;
        }
        catch (Exception e) {
            v = null;
        }
        return v;
    }*/
    
    public List<Map<String, Object>> getVacunas(Long idHijo) {
      StringBuilder countQuery = new StringBuilder();
      countQuery.append("select * ");
      countQuery.append(" from public.vacunas");
      countQuery.append(" where id_hijo ='"+idHijo.toString()+"'");
      List<Object[]> oResultList = em.createNativeQuery(countQuery.toString()).
              getResultList();
      if(oResultList.size()<=0){
    	  return null;
      }
      List<Map<String, Object>>  resultlist = new ArrayList<>();
      for (Object[] oResultArray : oResultList) {
          Map<String, Object> oMapResult = new HashMap<>();
          oMapResult.put("idVacuna", oResultArray[0]);
          oMapResult.put("idHijo", oResultArray[1]);
          oMapResult.put("nombreVacuna", oResultArray[2]);
          //System.out.println(oResultArray[3].toString());
          Timestamp timestamp = (Timestamp) oResultArray[3];
          Date fecha = null;
          String prueba = null;
          if(timestamp != null){
        	  fecha = new Date( timestamp.getTime());
              DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
              prueba = df.format(fecha);
          }
          oMapResult.put("fechaAplicacion",prueba );
          oMapResult.put("aplicada", oResultArray[4]);
          resultlist.add(oMapResult);
      }
      return resultlist;
    }
    
    
    @POST
    @Path("/obtener-por-hijo")
    public Response getVacunaHijo(@QueryParam("idHijo")  Long idHijo) throws Exception {
    	if(idHijo== null){
    		throw new Exception("Se requiere idHijo");
    	}
        return Response.ok(getVacunas(idHijo)).build();
    }
    
}
