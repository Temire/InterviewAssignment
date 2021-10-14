package com.temire.springbootexample.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temire.springbootexample.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//	Optional<Customer> findById(int ID);

	
	
	ArrayList<Customer> findByFirstNameContaining(String firstName);
	ArrayList<Customer> findByLastNameContaining(String lastName);
	
	default List<Customer> findByName(String firstName, String lastName){
		List<Customer> customers = new ArrayList<Customer>();
		for(Customer c: findAll() ) {
			if(c.getFirstName().equalsIgnoreCase(firstName)&&c.getLastName().equalsIgnoreCase(lastName))
				customers.add(c);
		}
		
		return customers;
	}
}
