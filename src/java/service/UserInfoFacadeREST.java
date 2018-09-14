/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import restclient.UserInfo;

/**
 *
 * @author nathan
 */
@Stateless
@Path("restclient.userinfo")
public class UserInfoFacadeREST extends AbstractFacade<UserInfo> {

    @PersistenceContext(unitName = "FIT4039DBPU")
    private EntityManager em;

    public UserInfoFacadeREST() {
        super(UserInfo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(UserInfo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, UserInfo entity) {
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
    public UserInfo find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserInfo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserInfo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    @GET
    @Path("checkUserExist/{username}/{password}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public UserInfo checkUserExist(@PathParam("username") String username, @PathParam("password") String password) {
        TypedQuery<UserInfo> query = em.createQuery("select u from UserInfo u "
                + "where u.username = :username and u.password = :password", UserInfo.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
    
    @GET
    @Path("checkAutoLogin/{userId}/{password}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public UserInfo checkAutoLogin(@PathParam("userId") int userId, @PathParam("password") String password) {
        TypedQuery<UserInfo> query = em.createQuery("select u from UserInfo u "
                + "where u.userId = :userId and u.password = :password", UserInfo.class);
        query.setParameter("userId", userId);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
    
    @GET
    @Path("checkUsernameExist/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkUsernameExist(@PathParam("username") String username) {
        TypedQuery<UserInfo> query = em.createQuery("select u from UserInfo u where u.username = :username", UserInfo.class);
        query.setParameter("username", username);
        return String.valueOf(query.getResultList().size() > 0);
    }
}
