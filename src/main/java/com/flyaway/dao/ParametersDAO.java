package com.flyaway.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.flyaway.entity.Admin;
import com.flyaway.entity.Airlines;
import com.flyaway.entity.Flights;
import com.flyaway.entity.Places;

import jakarta.servlet.http.HttpSession;

//import jakarta.persistence.EntityManager;

@Repository
@Transactional
@EnableTransactionManagement
public class ParametersDAO {
	
	@Autowired	
	private HibernateTransactionManager hbnTransactionManager;
//    private SessionFactory sessionFactory;
    
    public HibernateTransactionManager getHbnTransactionManager() {
		return hbnTransactionManager;
	}

	public void setHbnTransactionManager(HibernateTransactionManager hbnTransactionManager) {
		this.hbnTransactionManager = hbnTransactionManager;
	}
    
    
	
//	private JpaTransactionManager jpaTransactionManager;
//	
//	public JpaTransactionManager getJpaTransactionManager() {
//		return jpaTransactionManager;
//	}
//
//	public void setJpaTransactionManager(JpaTransactionManager jpaTransactionManager) {
//		this.jpaTransactionManager = jpaTransactionManager;
//	}
    
	




	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Places> getAllPlaces() {
	// VVVVIP	
	// Now this block from 57 to 58 is working for after adding @Transactional and @EnableTransactionManagement
	// Link that helped us https://www.baeldung.com/no-hibernate-session-bound-to-thread-exception	
		SessionFactory sessionFactory=hbnTransactionManager.getSessionFactory();
        return sessionFactory.getCurrentSession().createQuery("select p.places from Places p").list();
    
    // VVVVIP    
    // This block from 61 to 64 was already working with jpaTransactionManager
//    	List<EProductEntity> productList=new ArrayList<EProductEntity>();
//    	EntityManager em=jpaTransactionManager.getEntityManagerFactory().createEntityManager();
//    	productList=(List<EProductEntity>)em.createQuery("select T from EProductEntity T").getResultList();
//    	return productList;
        
        
    }
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Airlines> getAllAirlines(){
		SessionFactory sessionFactory=hbnTransactionManager.getSessionFactory();
        return sessionFactory.getCurrentSession().createQuery("select a.airlines from Airlines a").list();
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Flights> getTheFlights(String source,String destination,String airline){
		SessionFactory sessionFactory=hbnTransactionManager.getSessionFactory();
//        return sessionFactory.getCurrentSession().createQuery("from Flights f where f.source="+source+
//        		" and f.destination="+destination+" and f.airline="+airline).list();
		@SuppressWarnings("rawtypes")
		Query query=sessionFactory.getCurrentSession().createQuery("select f from Flights f where f.source= :source"+
        		" AND f.destination= :destination AND f.airline= :airline");
		query.setParameter("source", source);
		query.setParameter("destination", destination);
		query.setParameter("airline", airline);
		return query.list();
		
//		return sessionFactory.getCurrentSession().createQuery("from Flights f where f.ID=:id").setParameter("id","10001").list();
//		return sessionFactory.getCurrentSession().createQuery("from Flights f").list();
//		f.ID, f.source, f.destination, f.airline, f.price
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Flights> getTheFlightsExAirline(String source,String destination){
		SessionFactory sessionFactory=hbnTransactionManager.getSessionFactory();
//        return sessionFactory.getCurrentSession().createQuery("from Flights f where f.source="+source+
//        		" and f.destination="+destination).list();
        @SuppressWarnings("rawtypes")
		Query query= sessionFactory.getCurrentSession().createQuery("select f from Flights f where f.source= :source"+
        		" AND f.destination= :destination"); 
        query.setParameter("source", source);
        query.setParameter("destination", destination);
        return query.list();
		
		
		
//		return sessionFactory.getCurrentSession().createQuery("from Flights f where f.ID=:id").setParameter("id","10001").list();
//		return sessionFactory.getCurrentSession().createQuery("from Flights f").list();
//		f.ID, f.source, f.destination, f.airline, f.price
	}
	
	public List<Admin> getTheUserDetails(String firstName){
		SessionFactory sessionFactory=hbnTransactionManager.getSessionFactory();
		Query query= sessionFactory.getCurrentSession().createQuery("select a from Admin a where a.firstName= :firstName"); 
        query.setParameter("firstName", firstName);
        return query.list();
		
	}
	
	public List<Admin> getAllUsersDetails(){
		SessionFactory sessionFactory=hbnTransactionManager.getSessionFactory();
		Query query= sessionFactory.getCurrentSession().createQuery("from Admin");        
        return query.list();
		
	}
	
	public void changeUserPassword(String userFirstName, String userPassword){
		SessionFactory sessionFactory=hbnTransactionManager.getSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();		
		Admin a=session.get(Admin.class, userFirstName);		
		a.setPassword(userPassword);		
		session.persist(a);
		transaction.commit();		
		session.close();
	}

}
