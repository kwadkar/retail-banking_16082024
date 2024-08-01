package com.academy.miniproject.service;

import java.util.List;
import java.util.Optional;

import com.academy.miniproject.model.Account;

public interface AccountService {

	public List<Account> getAllAccountDtls();
	
	public Optional<Account> getAccountDtlsByAcctId(int accId);
	
	public String saveAccountData(Account account);
	
	public String deleteAccountData(int accId);
	
	public String updateAccountData(Account account);
}
