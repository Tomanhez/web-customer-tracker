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

	@Autowired // automaticly get our bean sessionFactory from s.m.c.d-servlet.xml
	private SessionFactory sessionFactory;

	@Override
	// @Transactional - this Transactional is transferred in CustomerService
	public List<Customer> getCustomers() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);

		List<Customer> customer = theQuery.getResultList();
		return customer;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		Session currentSession = sessionFactory.getCurrentSession();

		/*
		 * ways to save obiect in db 1 - session.save(..); // using INSERT in db 2 -
		 * session.update(..); // using UPADATE in db 3 - session.saveOrUpdate(..); if
		 * id exist hb use UPDATE if new INSTERT
		 */
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();

		Customer theCustomer = currentSession.get(Customer.class, theId);

		return theCustomer;
	}

	@Override
	public void delete(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		// or createQuerry("delete form Cutomer where id=:theCustomerId")
		Customer theCustomer = currentSession.get(Customer.class, theId);
		currentSession.delete(theCustomer);
	}

	@Override
	public List<Customer> searchCustomer(String theSearchName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Customer> theQuery = null;
		

		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery = currentSession.createQuery(
					"from Customer where lower(firstName) like :theName or lower(lastName) like :theName",
					Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		} else {
			// theSearchName is empty ... so just get all customers
			theQuery = currentSession.createQuery("from Customer", Customer.class);
		}
		List<Customer> theList = theQuery.getResultList();
		
		return theList;
	}

}
