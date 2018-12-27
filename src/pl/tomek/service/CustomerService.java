package pl.tomek.service;

import java.util.List;

import pl.tomek.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();
	
	public void saveCustomer(Customer theCustomer);
	
	public Customer getCustomer(int theId);

	public void delete(int theId);

	public List<Customer> searchCustomer(String theSearchName);
	
}
