package com.cg.onlineinsurance.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.exception.CustomerNotFoundException;
import com.cg.onlineinsurance.exception.DuplicatePolicyException;
import com.cg.onlineinsurance.exception.EmptyPolicyException;
import com.cg.onlineinsurance.exception.PolicyListEmptyException;
import com.cg.onlineinsurance.exception.PolicyNotFoundException;
import com.cg.onlineinsurance.service.IAdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IAdminService adminService;

	@PostMapping("/addPolicy")
	public ResponseEntity<String> addPolicy(@Valid @RequestBody Policy policy,Errors error) throws DuplicatePolicyException,EmptyPolicyException{
		if( error.hasFieldErrors("policyName"))
		{
			return new ResponseEntity<>("Fields cannot be empty", HttpStatus.OK);
		}
		else
		{adminService.addPolicy(policy);
		return new ResponseEntity<>("Policy added", HttpStatus.OK);
		}
	}

	@PutMapping("/updatePolicy")
	public ResponseEntity<String> updatePolicy(@Valid @RequestBody Policy policy) throws PolicyNotFoundException {
		adminService.updatePolicy(policy);
		return new ResponseEntity<>("Policy Updated", HttpStatus.OK);
	}

	@GetMapping("/selectAll")
	public ResponseEntity<List<Policy>> displayPolicies() throws PolicyListEmptyException {
		List<Policy> policyList = adminService.viewAllPolicies();

		return new ResponseEntity<>(policyList, HttpStatus.OK);
	}

	@DeleteMapping("/deletePolicy/{id}")
	public ResponseEntity<String> deletePolicy(@PathVariable int id) throws PolicyNotFoundException{
		 adminService.removePolicy(id);
		return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
	}

	@GetMapping("/getPolicyById/{code}")
	public ResponseEntity <Policy> getPolicyById(@PathVariable int code) {
		 Policy policy=adminService.getPolicyById(code);

		return new ResponseEntity<>(policy, HttpStatus.OK);
	}
	
	@GetMapping("/viewAllCustomer")
	public ResponseEntity<List<Customer>> viewAllCustomer() throws CustomerNotFoundException {
		List<Customer> policyList = adminService.viewAllCustomer();
		

		return new ResponseEntity<>(policyList, HttpStatus.OK);
	}
	@GetMapping("/viewCustomerById/{code}")
	public ResponseEntity <Customer> getCustomerById(@PathVariable int code) {
		 Customer policy=adminService.getCustomerById(code);

		return new ResponseEntity<>(policy, HttpStatus.OK);
	}
	
}
