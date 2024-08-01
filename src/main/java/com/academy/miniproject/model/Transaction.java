package com.academy.miniproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "txn_dtls")
@Data
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "txn_id")
    private Long txnId;
	
	@Column(name = "txn_amount")
    private Double amount;
	
	@Column(name = "txn_date")
    private Date transactionDate;

	@ManyToOne
    @JoinColumn(name = "account_id")
	@JsonIgnore
    private Account account;
}
