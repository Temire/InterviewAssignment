package com.temire.springbootexample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanPackage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID;

	@Getter
	@Setter
	private String loanRefName;
	private double loanAmount;
	private String loanClass;
	private int loanValidityDays;
	
	


}
