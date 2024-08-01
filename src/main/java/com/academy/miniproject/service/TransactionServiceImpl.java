package com.academy.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.academy.miniproject.model.Account;
import com.academy.miniproject.model.Transaction;
import com.academy.miniproject.repository.AccountRepository;
import com.academy.miniproject.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {


	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Transactional(propagation = Propagation.REQUIRED)
	public void transferFunds(int fromAccountId, int toAccountId, Double amount) {
		Account fromAccount = accountRepository.findById(fromAccountId)
				.orElseThrow(() -> new RuntimeException("Account not found"));

		Account toAccount = accountRepository.findById(toAccountId)
				.orElseThrow(() -> new RuntimeException("Account not found"));

		if (fromAccount.getBalance() < amount) {
			throw new RuntimeException("Insufficient funds");
		}

		fromAccount.setBalance(fromAccount.getBalance() - amount);
		toAccount.setBalance(toAccount.getBalance() + amount);

		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);

		Transaction debitTransaction = new Transaction();
		debitTransaction.setAccount(fromAccount);
		debitTransaction.setAmount(-amount);
		debitTransaction.setTransactionDate(new java.util.Date());

		Transaction creditTransaction = new Transaction();
		creditTransaction.setAccount(toAccount);
		creditTransaction.setAmount(amount);
		creditTransaction.setTransactionDate(new java.util.Date());

		transactionRepository.save(debitTransaction);
		transactionRepository.save(creditTransaction);
	}

	public List<Transaction> getAllTxnDtls() {
		return transactionRepository.findAll();
	}

	public Transaction getTxnDtlsByTxnId(int txnId) {
		Transaction transaction =transactionRepository.findById(txnId).get();
		return transaction;
	}

}
