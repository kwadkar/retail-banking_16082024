package com.academy.miniproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.miniproject.exception.ResourceNotFoundException;
import com.academy.miniproject.model.Account;
import com.academy.miniproject.model.Customer;
import com.academy.miniproject.model.request.AccountRequest;
import com.academy.miniproject.model.request.CustomerRequest;
import com.academy.miniproject.model.request.CustomerResponse;
import com.academy.miniproject.repository.AccountRepository;
import com.academy.miniproject.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public List<Customer> getAllCustomerDtls() {
		return customerRepository.findAll();
	}

	public Optional<Customer> getCustomerDtlsByCustId(int custId) {
		Optional<Customer> customer = customerRepository.findById(custId);
		return customer;
	}


	public String deleteCustomerData(int custId) {
		customerRepository.deleteById(custId);
		return "customer deleted..";
	}


	public CustomerResponse saveCustomerAndAccountData(CustomerRequest customerRequest) {
		Customer custObj = new  Customer();
		custObj.setCustName(customerRequest.getCustName());
		custObj.setCustAddress(customerRequest.getCustAddress());
				
		customerRepository.save(custObj);
		
		//account updates
		this.createOrUpdateAccountDtl(custObj,customerRequest.getAccountList());
		
		//response customer 
		CustomerResponse customerResponse =this.createCustomerResponse(custObj , customerRequest.getAccountList());
		
		return customerResponse;
	}
	

	private CustomerResponse createCustomerResponse(Customer custObj, List<AccountRequest> accountList) {
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setCustId(custObj.getCustId());
		customerResponse.setCustName(custObj.getCustName());
		customerResponse.setCustAddress(custObj.getCustAddress());
		customerResponse.setAccountList(accountList);	
		return customerResponse;
	}


	private void createOrUpdateAccountDtl(Customer custObj,List<AccountRequest> accountList) {
		Account account= null;
		for(AccountRequest accObj :accountList) {
			account = new Account();
			account.setAccountNumber(accObj.getAccountNumber());
			account.setBalance(accObj.getBalance());
			account.setCustomer(custObj);
			accountRepository.save(account);
		}
		
	}

	public CustomerResponse updateCustomerData(int custId, CustomerRequest customerRequest) {
		
		Optional<Customer> customer= Optional.ofNullable(this.getCustomerDtlsByCustId(custId)
				.orElseThrow(()-> new ResourceNotFoundException("Customer Id not found with id:"+custId)));
		if(customer.isEmpty()) {
			this.saveCustomerAndAccountData(customerRequest);
		}else {
			customer.get().setCustName(customerRequest.getCustName());
			customer.get().setCustAddress(customerRequest.getCustAddress());
			this.createOrUpdateAccountDtl(customer.get(),customerRequest.getAccountList());
		}
		//response customer 
		CustomerResponse customerResponse = this.createCustomerResponse(customer.get() , customerRequest.getAccountList());
				
		return customerResponse;
	}


}

