package com.temire.springbootexample.model;

import java.sql.Date;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplications {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID;

	@Getter
	@Setter
	private int customerID;
	private int loanID;
	private int loanDays;
	private Date appliedDate;
	private int status = 0; // 0=new, 1=pending, 2=reviewing, 3=approved, 4=disbursed, -1=rejected
	private String statusDesc;
	
	
	public static boolean checkEligibility(Optional<Customer> c, Optional<LoanPackage> l) {
		if(c.get().getIncome() > (l.get().getLoanAmount()/5))
			return true;
		
		return false;
	}

}
