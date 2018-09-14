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
import restclient.ChildCategory;

/**
 *
 * @author nathan
 */
@Stateless
@Path("restclient.childcategory")
public class ChildCategoryFacadeREST extends AbstractFacade<ChildCategory> {

    @PersistenceContext(unitName = "FIT4039DBPU")
    private EntityManager em;

    public ChildCategoryFacadeREST() {
        super(ChildCategory.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(ChildCategory entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, ChildCategory entity) {
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
    public ChildCategory find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ChildCategory> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ChildCategory> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("findAllChildCategories/{userId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ChildCategory> findAllParentCategories(@PathParam("userId") int userId) {
        TypedQuery<ChildCategory> query = em.createQuery("select p from ChildCategory p where p.parentCategoryId.userId.userId = :userId", ChildCategory.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @GET
    @Path("findAllChildCategoriesFromOneParentCategory/{userId}/{parentCategoryId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ChildCategory> findAllParentCategoriesFromOneParentCategory(@PathParam("userId") int userId, @PathParam("parentCategoryId") int parentCategoryId) {
        TypedQuery<ChildCategory> query = em.createQuery("select c from ChildCategory c "
                + "where c.parentCategoryId.userId.userId = :userId "
                + "and c.parentCategoryId.parentCategoryId = :parentCategoryId", ChildCategory.class);
        query.setParameter("userId", userId);
        query.setParameter("parentCategoryId", parentCategoryId);
        return query.getResultList();
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
