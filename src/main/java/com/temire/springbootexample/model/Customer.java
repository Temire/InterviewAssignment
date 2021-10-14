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
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Getter
	@Setter
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
//	private String dob;
//	private String bvn;
//	private String address;
	private double income;
}
