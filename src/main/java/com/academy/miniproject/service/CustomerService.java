package com.academy.miniproject.service;

import java.util.List;
import java.util.Optional;

import com.academy.miniproject.model.Customer;
import com.academy.miniproject.model.request.CustomerRequest;
import com.academy.miniproject.model.request.CustomerResponse;


public interface CustomerService {

	public List<Customer> getAllCustomerDtls();
	
	public Optional<Customer> getCustomerDtlsByCustId(int custId);
	
	public String deleteCustomerData(int custId);
	
	public CustomerResponse saveCustomerAndAccountData(CustomerRequest customerRequest);
	
	public CustomerResponse updateCustomerData(int custId, CustomerRequest customerRequest);
}
