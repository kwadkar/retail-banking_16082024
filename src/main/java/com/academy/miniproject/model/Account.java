package com.academy.miniproject.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "acc_dtls")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "acc_id")
	private int accId ;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "balance")
	private double balance;
	
	@ManyToOne
    @JoinColumn(name = "customer_id" , nullable=false)
	//@JsonManagedReference
	@JsonIgnore
    private Customer customer;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	@JsonInclude
    private List<Transaction> transactions;
}
