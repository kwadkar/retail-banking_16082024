package com.academy.miniproject.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.academy.miniproject.model.Customer;

@Repository
public interface CustomerRepository2 extends PagingAndSortingRepository<Customer, Integer>{

}
