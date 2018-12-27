package pl.tomek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.tomek.entity.Customer;
import pl.tomek.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerServiece;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		List<Customer> theCustomers = customerServiece.getCustomers();

		theModel.addAttribute("customers", theCustomers);

		return "list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Customer theCustomer = new Customer();

		theModel.addAttribute("customer", theCustomer);

		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

		customerServiece.saveCustomer(theCustomer);

		return "redirect:/customer/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpadate(@RequestParam("customerId") int theId, Model theModel) {
		Customer theCustomer = customerServiece.getCustomer(theId);

		theModel.addAttribute("customer", theCustomer);
		return "customer-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {
		
		customerServiece.delete(theId);
		return "redirect:/customer/list";
	}

	@PostMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		
		List<Customer> theCustomers = customerServiece.searchCustomer(theSearchName);
		
		// add the customers to the model
        theModel.addAttribute("customers", theCustomers);
		return "list-customers";
	}

}
