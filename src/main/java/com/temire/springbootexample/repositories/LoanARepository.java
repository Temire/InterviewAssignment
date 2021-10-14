package com.temire.springbootexample.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temire.springbootexample.model.LoanApplications;

public interface LoanARepository extends JpaRepository<LoanApplications, Integer> {

	List<LoanApplications> findByCustomerID(int customerID);
	
	default LoanApplications findByCustomerIDStatus(int customerID){
		for(LoanApplications l:findByCustomerID(customerID)) {
			if(l.getStatus()==0)
				return l;				
		}		
		return null;		
	}
}
