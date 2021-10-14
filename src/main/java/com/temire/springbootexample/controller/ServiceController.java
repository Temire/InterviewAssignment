package com.temire.springbootexample.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.temire.springbootexample.repositories.*;
import com.temire.springbootexample.model.*;

@RestController
@RequestMapping("/api")
public class ServiceController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	LoanPRepository loanPRepository;

	@Autowired
	LoanARepository loanARepository;

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllcustomers(@RequestParam(required = false) String firstname,
			@RequestParam(required = false) String lastname) {
		try {
			List<Customer> customers = new ArrayList<Customer>();

			if (firstname == null && lastname == null)
				customerRepository.findAll().forEach(customers::add);
			else {
				if (firstname != null && lastname == null)
					customerRepository.findByFirstNameContaining(firstname).forEach(customers::add);
				else if (firstname == null && lastname != null)
					customerRepository.findByLastNameContaining(lastname).forEach(customers::add);
				else if (firstname != null && lastname != null)
					customerRepository.findByName(firstname, lastname).forEach(customers::add);

			}

			if (customers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(customers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
		Optional<Customer> CustomerData = customerRepository.findById(id);

		if (CustomerData.isPresent()) {
			return new ResponseEntity<>(CustomerData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer Customer) {
		try {
			Customer _Customer = customerRepository.save(Customer);
			return new ResponseEntity<>(_Customer, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer Customer) {
		Optional<Customer> CustomerData = customerRepository.findById(id);

		if (CustomerData.isPresent()) {
			Customer _Customer = CustomerData.get();
			_Customer = Customer;
			return new ResponseEntity<>(customerRepository.save(_Customer), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") int id) {
		try {
			customerRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/customers")
	public ResponseEntity<HttpStatus> deleteAllcustomers() {
		try {
			customerRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/loanpackages")
	public ResponseEntity<List<LoanPackage>> getAllloanpackages(@RequestParam(required = false) String loanClass) {
		try {
			List<LoanPackage> loanpackages = new ArrayList<LoanPackage>();

			if (loanClass == null)
				loanPRepository.findAll().forEach(loanpackages::add);
			else
				loanPRepository.findByLoanClass(loanClass).forEach(loanpackages::add);

			if (loanpackages.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(loanpackages, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/loanpackages/{id}")
	public ResponseEntity<LoanPackage> getLoanPackageById(@PathVariable("id") int id) {
		Optional<LoanPackage> LoanPackageData = loanPRepository.findById(id);

		if (LoanPackageData.isPresent()) {
			return new ResponseEntity<>(LoanPackageData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/loanpackages")
	public ResponseEntity<LoanPackage> createLoanPackage(@RequestBody LoanPackage loanPackage) {
		try {
			LoanPackage _LoanPackage = loanPRepository.save(loanPackage);
			return new ResponseEntity<>(_LoanPackage, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/loanpackages/{id}")
	public ResponseEntity<LoanPackage> updateLoanPackage(@PathVariable("id") int id,
			@RequestBody LoanPackage LoanPackage) {
		Optional<LoanPackage> LoanPackageData = loanPRepository.findById(id);

		if (LoanPackageData.isPresent()) {
			LoanPackage _LoanPackage = LoanPackageData.get();
			_LoanPackage = LoanPackage;
			return new ResponseEntity<>(loanPRepository.save(_LoanPackage), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/loanpackages/{id}")
	public ResponseEntity<HttpStatus> deleteLoanPackage(@PathVariable("id") int id) {
		try {
			loanPRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/loanpackages")
	public ResponseEntity<HttpStatus> deleteAllloanpackages() {
		try {
			loanPRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/loanapplications")
	public ResponseEntity<List<LoanApplications>> getAllloanapplications(
			@RequestParam(required = false) int customerID) {
		try {
			List<LoanApplications> loanapplications = new ArrayList<LoanApplications>();

			if (Objects.isNull(customerID))
				loanARepository.findAll().forEach(loanapplications::add);
			else
				loanARepository.findByCustomerID(customerID).forEach(loanapplications::add);

			if (loanapplications.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(loanapplications, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/loanapplications/{id}")
	public ResponseEntity<LoanApplications> getLoanApplicationsById(@PathVariable("id") int id) {
		Optional<LoanApplications> LoanApplicationsData = loanARepository.findById(id);

		if (LoanApplicationsData.isPresent()) {
			return new ResponseEntity<>(LoanApplicationsData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/loanapplications")
	public ResponseEntity<LoanApplications> createLoanApplications(@RequestBody LoanApplications loanApplications) {
		try {
			LoanApplications LoanApplicationsData = loanARepository
					.findByCustomerIDStatus(loanApplications.getCustomerID());
			if (!Objects.isNull(LoanApplicationsData))
				return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
			else {
				if (LoanApplications.checkEligibility(customerRepository.findById(loanApplications.getCustomerID()),
						loanPRepository.findById(loanApplications.getLoanID()))) {
					LoanApplications _LoanApplications = loanARepository.save(loanApplications);
					return new ResponseEntity<>(_LoanApplications, HttpStatus.CREATED);
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@PutMapping("/loanapplications/{id}")
	public ResponseEntity<LoanApplications> updateLoanApplications(@PathVariable("id") int id,
			@RequestBody LoanApplications LoanApplications) {
		Optional<LoanApplications> LoanApplicationsData = loanARepository.findById(id);

		if (LoanApplicationsData.isPresent()) {
			LoanApplications _LoanApplications = LoanApplicationsData.get();
			_LoanApplications = LoanApplications;
			return new ResponseEntity<>(loanARepository.save(_LoanApplications), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/loanapplications/{id}")
	public ResponseEntity<HttpStatus> deleteLoanApplications(@PathVariable("id") int id) {
		try {
			loanARepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/loanapplications")
	public ResponseEntity<HttpStatus> deleteAllloanapplications() {
		try {
			loanARepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
