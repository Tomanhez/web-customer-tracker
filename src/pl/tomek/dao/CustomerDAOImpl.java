package pl.tomek.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.tomek.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired//automaticly get our bean sessionFactory from s.m.c.d-servlet.xml
	private SessionFactory sessionFactory;
	
	
	@Override
	//@Transactional - this Transactional is transferred in CustomerService
	public List<Customer> getCustomers() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName",Customer.class);

		
		List<Customer> customer = theQuery.getResultList();
		return customer;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(theCustomer);
	}

}
