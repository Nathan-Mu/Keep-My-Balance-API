/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
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
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import restclient.FundChange;

/**
 *
 * @author nathan
 */
@Stateless
@Path("restclient.fundchange")
public class FundChangeFacadeREST extends AbstractFacade<FundChange> {

    @PersistenceContext(unitName = "FIT4039DBPU")
    private EntityManager em;

    public FundChangeFacadeREST() {
        super(FundChange.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(FundChange entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, FundChange entity) {
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
    public FundChange find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<FundChange> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<FundChange> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("findMonthlyTotal/{month}/{year}/{userId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String findMonthlyTotal(@PathParam("month") Integer month, @PathParam("year") Integer year, @PathParam("changeType") String changeType, @PathParam("userId") int userId)
    {
        TypedQuery<FundChange> query = em.createQuery("select f from FundChange f "
                + "where function('month', f.changeDate) = :month "
                + "and function('year', f.changeDate) = :year "
                + "and f.changeType = :changeType "
                + "and f.accountId.userId.userId = :userId", FundChange.class);
        query.setParameter("month", month);
        query.setParameter("year", year);
        query.setParameter("userId", userId);
        String[] array = {"Income", "Expense"};
        JSONObject json = new JSONObject();
        for (String s: array)
        {
            query.setParameter("changeType", s);
            List<FundChange> list = query.getResultList();
            double total = 0.00;
            for (FundChange f: list){
                total += f.getAmount();
            }
            try {
                json.put(s, total);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return json.toString();
    }
    
    @GET
    @Path("findMonthlyChangeOrderByDate/{month}/{year}/{userId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<FundChange> findMonthlyBill(@PathParam("month") Integer month, @PathParam("year") Integer year, @PathParam("userId") Integer userId)
    {
        TypedQuery<FundChange> query = em.createQuery("select f from FundChange f "
                + "where function('month', f.changeDate) = :month "
                + "and function('year', f.changeDate) = :year "
                + "and f.accountId.userId.userId = :userId "
                + "order by f.changeDate", FundChange.class);
        query.setParameter("month", month);
        query.setParameter("year", year);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test()
    {
        Gson gson = new Gson();
        TypedQuery<FundChange> query = em.createQuery("select f from FundChange f where f.changeId = 1", FundChange.class);
        return gson.toJson(query.getSingleResult());
    }
    
    @GET
    @Path("isAccountRemovableByAccountType/{accountType}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isAccountRemovableByAccountType(@PathParam("accountType") String accountType)
    {
        TypedQuery<FundChange> query = em.createQuery("select f from FundChange f where f.accountId.accountType = :accountType", FundChange.class);
        query.setParameter("accountType", accountType);
        List<FundChange> list = query.getResultList();
        return list.isEmpty();
    }
    
    @GET
    @Path("isAccountRemovable/{accountId}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isAccountRemovable(@PathParam("accountId") int accountId)
    {
        TypedQuery<FundChange> query = em.createQuery("select f from FundChange f where f.accountId.accountId = :accountId", FundChange.class);
        query.setParameter("accountId", accountId);
        List<FundChange> list = query.getResultList();
        return list.isEmpty();
    }
    
    @GET
    @Path("isChildCategoryRemovable/{childCategoryId}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isChildCategoryRemovable(@PathParam("childCategoryId") int childCategoryId) {
        TypedQuery<FundChange> query = em.createQuery("select f from FundChange f where f.childCategoryId.childCategoryId = :childCategoryId", FundChange.class);
        query.setParameter("childCategoryId", childCategoryId);
        List<FundChange> list = query.getResultList();
        return list.isEmpty();
    }
    
    @GET
    @Path("findHomePageStatics/{userId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String findHomePageStatics(@PathParam("userId") Integer userId)
    {
        JSONObject json = new JSONObject();
        try
        {
            json.put("monthIncome", getCurrentMonthStatics(userId).get(0));
            json.put("monthExpense", getCurrentMonthStatics(userId).get(1));
            json.put("todayIncome", getTodayStatics(userId).get(0));
            json.put("todayExpense", getTodayStatics(userId).get(1));
            json.put("7daysIncome", get7DaysStatics(userId).get(0));
            json.put("7daysExpense", get7DaysStatics(userId).get(1));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return json.toString();
    }

    
    
    public String getPastDate(int past) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);  
        Date today = calendar.getTime();  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        String result = format.format(today);
        return result;  
   }
    
    @GET
    @Path("find7DaysTotal/{userId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String find7DaysTotal(@PathParam("userId") Integer userId)
    {
        JSONObject json = new JSONObject();
        try
        {
            json.put("income", get7DaysStatics(userId).get(0));
            json.put("expense", get7DaysStatics(userId).get(1));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return json.toString();
    }
    
    public int getCurrentYear()
    {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }
    
    public int getCurrentMonth()
    {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }
    
    public List<Double> get7DaysStatics(int userId)
    {
        String date = getPastDate(7);
        TypedQuery<FundChange> query = em.createQuery("select f from FundChange f "
                + "where f.changeDate > '" + date + "' "
                + "and f.changeType = :changeType "
                + "and f.accountId.userId.userId = :userId", FundChange.class);
        query.setParameter("userId", userId);
        String[] array = {"Income", "Expense"};
        List<Double> staticsList = new ArrayList<Double>();
        for (String s: array)
        {
            query.setParameter("changeType", s);
            List<FundChange> list = query.getResultList();
            double total = 0.00;
            for (FundChange f: list){
                total += f.getAmount();
            }
            staticsList.add(total);
        }
        return staticsList;
    }
    
    public List<Double> getTodayStatics(int userId)
    {
        String date = getPastDate(0);
        TypedQuery<FundChange> query = em.createQuery("select f from FundChange f "
                + "where f.changeDate = '" + date + "' "
                + "and f.changeType = :changeType "
                + "and f.accountId.userId.userId = :userId", FundChange.class);
        query.setParameter("userId", userId);
        String[] array = {"Income", "Expense"};
        List<Double> staticsList = new ArrayList<Double>();
        for (String s: array)
        {
            query.setParameter("changeType", s);
            List<FundChange> list = query.getResultList();
            double total = 0.00;
            for (FundChange f: list){
                total += f.getAmount();
            }
            staticsList.add(total);
        }
        return staticsList;
    }
    
    public List<Double> getMonthlyStatics(int month, int year, int userId)
    {
        TypedQuery<FundChange> query = em.createQuery("select f from FundChange f where "
                + "function('month', f.changeDate) = :month and function('year', f.changeDate) = :year "
                + "and f.changeType = :changeType and f.accountId.userId.userId = :userId", FundChange.class);
        query.setParameter("month", month);
        query.setParameter("year", year);
        query.setParameter("userId", userId);
        String[] array = {"Income", "Expense"};
        List<Double> staticsList = new ArrayList<Double>();
        for (String s: array)
        {
            query.setParameter("changeType", s);
            List<FundChange> list = query.getResultList();
            double total = 0.00;
            for (FundChange f: list){
                total += f.getAmount();
            }
            staticsList.add(total);
        }
        return staticsList;
    }
    
    public List<Double> getCurrentMonthStatics(int userId)
    {
        return getMonthlyStatics(getCurrentMonth(), getCurrentYear(), userId);
    }
    
    @GET
    @Path("findMonthlyExpenseRate/{month}/{year}/{userId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String findMonthlyExpenseRate(@PathParam("month") Integer month, @PathParam("year") Integer year, @PathParam("userId") Integer userId)
    {
        TypedQuery<FundChange> query = em.createQuery("select f from FundChange f "
                + "where function('month', f.changeDate) = :month "
                + "and function('year', f.changeDate) = :year "
                + "and f.accountId.userId.userId = :userId "
                + "order by f.changeDate", FundChange.class);
        query.setParameter("month", month);
        query.setParameter("year", year);
        query.setParameter("userId", userId);
        List<FundChange> list = query.getResultList();
        Double totalAmount = 0.0;
        Map<String, Double> map = new HashMap<String, Double>();
        for (FundChange f: list) {
            if (f.getChangeType().equalsIgnoreCase("expense"))
            {
                totalAmount += f.getAmount();
                String key = f.getChildCategoryId().getParentCategoryId().getParentCategoryName();
                if (map.containsKey(key))
                    map.put(key, map.get(key) + f.getAmount());
                else
                    map.put(key, f.getAmount());
            }
        }
        
        JSONArray jsonArray = new JSONArray();
        if (totalAmount != 0)
        {
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                try {
                    JSONObject json = new JSONObject();
                    String categoryName = (String) entry.getKey();
                    Double categoryAmount = (Double) entry.getValue();
                    json.put("category", categoryName);
                    json.put("rate", categoryAmount/totalAmount);
                    jsonArray.put(json);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonArray.toString();
    }
    
    

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
