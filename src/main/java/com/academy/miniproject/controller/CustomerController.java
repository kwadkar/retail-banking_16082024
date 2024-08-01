package com.academy.miniproject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
}
