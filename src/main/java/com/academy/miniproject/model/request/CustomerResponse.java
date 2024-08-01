package com.academy.miniproject.model.request;

import java.util.List;

import lombok.Data;

@Data
public class CustomerResponse {

	private int custId;
	
	private String custName;
	
	private String custAddress;

	List<AccountRequest> accountList;
}
