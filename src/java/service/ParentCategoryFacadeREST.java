/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.Collection;
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
import restclient.ParentCategory;

/**
 *
 * @author nathan
 */
@Stateless
@Path("restclient.parentcategory")
public class ParentCategoryFacadeREST extends AbstractFacade<ParentCategory> {

    @PersistenceContext(unitName = "FIT4039DBPU")
    private EntityManager em;

    public ParentCategoryFacadeREST() {
        super(ParentCategory.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(ParentCategory entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, ParentCategory entity) {
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
    public ParentCategory find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ParentCategory> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ParentCategory> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("findAllParentCategories/{userId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ParentCategory> findAllParentCategories(@PathParam("userId") int userId) {
        TypedQuery<ParentCategory> query = em.createQuery("select p from ParentCategory p where p.userId.userId = :userId", ParentCategory.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @GET
    @Path("findExpenseParentCategories/{userId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ParentCategory> findExpenseParentCategories(@PathParam("userId") int userId) {
        TypedQuery<ParentCategory> query = em.createQuery("select p from ParentCategory p "
                + "where p.userId.userId = :userId "
                + "and p.parentCategoryName != 'Income' "
                + "and p.parentCategoryName != 'Transfer'", ParentCategory.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @GET
    @Path("findIncomeParentCategories/{userId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ParentCategory> findIncomeParentCategories(@PathParam("userId") int userId) {
        TypedQuery<ParentCategory> query = em.createQuery("select p from ParentCategory p "
                + "where p.userId.userId = :userId "
                + "and p.parentCategoryName = 'Income'", ParentCategory.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
