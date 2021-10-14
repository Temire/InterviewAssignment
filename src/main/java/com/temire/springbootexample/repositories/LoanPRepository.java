package com.temire.springbootexample.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temire.springbootexample.model.LoanPackage;

public interface LoanPRepository extends JpaRepository<LoanPackage, Integer> {

	List<LoanPackage> findByLoanClass(String loanClass);
}
