package com.academy.miniproject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.miniproject.exception.ResourceNotFoundException;
import com.academy.miniproject.model.Account;
import com.academy.miniproject.service.AccountServiceImpl;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	AccountServiceImpl accountService;
	
	@GetMapping("/getAllData")
	public ResponseEntity getAccountDetails() {
		return new ResponseEntity(accountService.getAllAccountDtls(), HttpStatus.OK) ;
	}
	
	@GetMapping("/getAccData/{id}")
	public ResponseEntity<Optional<Account>> getAccountDtlsById(@PathVariable("id") int accId ){
		Optional<Account> account= Optional.ofNullable(accountService.getAccountDtlsByAcctId(accId)
				.orElseThrow(()-> new ResourceNotFoundException("Account Id not found with id:"+accId)));
		return ResponseEntity.ok(account);
	}
	
	/*
	@PostMapping("/createAccount")
	public String saveAccountData(@RequestBody Account account) {
		String saveMsg = accountService.saveAccountData(account);
		return saveMsg;  
	}
	
	@DeleteMapping("/deleteAccount/{custId}")
	public String deleteAccountData(@PathVariable int custId) {
		return accountService.deleteAccountData(custId);
	}
	
	@PutMapping("/updateAccount/{custId}")
	public String updateAccountData(@RequestBody Account account) {
		return accountService.updateAccountData(account);
	}*/
}
