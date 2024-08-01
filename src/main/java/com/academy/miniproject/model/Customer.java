package com.academy.miniproject.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "cust_dtls")
/*
 * @Getter
 * 
 * @Setter
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 */
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cust_id")
	private int custId;
	
	@Column(name = "cust_name")
	private String custName;
	
	@Column(name="cust_address")
	private String custAddress;
	
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    //@JsonBackReference
    @JsonInclude
	private List<Account> accountList;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int custId, String custName, String custAddress, List<Account> accountList) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custAddress = custAddress;
		this.accountList = accountList;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", custAddress=" + custAddress
				+ ", accountList=" + accountList + "]";
	}

    
}
