package pl.tomek.service;

import java.util.List;

import pl.tomek.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();
	
	public void saveCustomer(Customer theCustomer);
	
}
