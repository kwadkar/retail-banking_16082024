package com.academy.miniproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.miniproject.model.Account;
import com.academy.miniproject.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public List<Account> getAllAccountDtls() {
		return accountRepository.findAll();
	}

	@Override
	public Optional<Account> getAccountDtlsByAcctId(int accId) {
		Optional<Account> account = accountRepository.findById(accId);
		return account;
	}

	@Override
	public String saveAccountData(Account account) {
		accountRepository.save(account);
		return "Customer saved..";
	}

	@Override
	public String deleteAccountData(int accId) {
		accountRepository.deleteById(accId);
		return "customer deleted..";
	}

	@Override
	public String updateAccountData(Account account) {
		accountRepository.save(account);
		return "customer updated";
	}

	

	
}
