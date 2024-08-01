package com.academy.miniproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.miniproject.model.Transaction;
import com.academy.miniproject.service.TransactionServiceImpl;

@RestController
@RequestMapping("/trasanction")
public class TransactionController {

	@Autowired
	TransactionServiceImpl transactionService;
	
	//http://localhost:8080/trasanction/transfer/?fromAccountId=1&toAccountId=2&amount=100
	@PostMapping("/transfer")
	public String transferFunds(@RequestParam int fromAccountId, @RequestParam int toAccountId, @RequestParam Double amount) {
		transactionService.transferFunds(fromAccountId, toAccountId, amount);
		return "success";
		
	}
	
	@GetMapping("/getAllData")
	public ResponseEntity getTxnDetails() {
		return new ResponseEntity(transactionService.getAllTxnDtls(), HttpStatus.OK) ;
	}
	
	@GetMapping("/getTxnData/{txnId}")
	public ResponseEntity<Transaction> getCustomerDtlsById(@PathVariable int txnId ){
		return new ResponseEntity<Transaction>(transactionService.getTxnDtlsByTxnId(txnId), HttpStatus.OK);
	}
	
}
