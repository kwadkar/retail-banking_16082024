package com.academy.miniproject.service;

import java.util.List;

import com.academy.miniproject.model.Transaction;

public interface TransactionService {
	public void transferFunds(int fromAccountId, int toAccountId, Double amount);
	
	public List<Transaction> getAllTxnDtls();
	
}
