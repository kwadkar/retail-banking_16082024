package com.academy.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academy.miniproject.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
