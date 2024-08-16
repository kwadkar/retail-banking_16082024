package com.academy.miniproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.miniproject.exception.ResourceNotFoundException;
import com.academy.miniproject.model.Customer;
import com.academy.miniproject.model.request.CustomerRequest;
import com.academy.miniproject.model.request.CustomerResponse;
import com.academy.miniproject.repository.CustomerRepository2;
import com.academy.miniproject.service.CustomerServiceImpl;

@RestController()
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	CustomerServiceImpl customerService;
	
	@GetMapping("/message")
	public String getMessage() {
		return "Good Morning";
	}
	
	@GetMapping("/getAllData")
	public ResponseEntity getCustomerDetails() {
		return new ResponseEntity(customerService.getAllCustomerDtls(), HttpStatus.OK) ;
	}
	
	@GetMapping("/getCustData/{id}")
	public ResponseEntity<Optional<Customer>> getCustomerDtlsById(@PathVariable("id") int custId ){
		//return new ResponseEntity<Customer>(customerService.getCustomerDtlsByCustId(custId), HttpStatus.OK);
		
		Optional<Customer> customer= Optional.ofNullable(customerService.getCustomerDtlsByCustId(custId)
				.orElseThrow(()-> new ResourceNotFoundException("Customer Id not found with id:"+custId)));
		return ResponseEntity.ok(customer);
		
		
	}
	
	@PostMapping("/createCustomer")
	public ResponseEntity<CustomerResponse> saveCustomerData(@RequestBody CustomerRequest customerRequest) {
		return new ResponseEntity<>(customerService.saveCustomerAndAccountData(customerRequest), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteCustomer/{custId}")
	public String deleteCustomerData(@PathVariable int custId) {
		return customerService.deleteCustomerData(custId);
	}
	
	@PutMapping("/updateCustomer/{custId}")
	public ResponseEntity<CustomerResponse> updateCustomerData(@PathVariable int custId,@RequestBody CustomerRequest customerRequest) {	
		return new ResponseEntity<>(customerService.updateCustomerData(custId,customerRequest), HttpStatus.OK);
	}
	
	@Autowired
	CustomerRepository2 customerRepository2;
	
	@GetMapping("/getAllDataTest")
	public ResponseEntity getCustomerDetailsSort() {
		//page and size
		//only paging
		Pageable pageable = PageRequest.of(0, 3);
		Page<Customer> page = customerRepository2.findAll(pageable);
		
		return new ResponseEntity(page, HttpStatus.OK) ;
		
		
		
		//only sorting
		//List<Customer> lstCust = (List<Customer>) customerRepository2.findAll(Sort.by("custName"));
		//ResponseEntity responseEntity = new ResponseEntity(lstCust, HttpStatus.OK);
		//return responseEntity;
		
		//sortng with descending order
		//List<Customer> lstCust = (List<Customer>) customerRepository2.findAll(Sort.by("custName").descending());
		//ResponseEntity responseEntity = new ResponseEntity(lstCust, HttpStatus.OK);
		//return responseEntity;
		
		//paging and sorting
		//Pageable pagingSort = PageRequest.of(0, 3, Sort.by("custName"));
		//Page<Customer> pageSort = customerRepository2.findAll(pagingSort);
		//return new ResponseEntity(pageSort, HttpStatus.OK) ;
		
	}
	
}
