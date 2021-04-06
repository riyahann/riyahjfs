package com.cg.onlineinsurance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineinsurance.dto.CustomerDTO;
import com.cg.onlineinsurance.dto.CustomerDetailsDTO;
import com.cg.onlineinsurance.dto.PolicyDetailsDTO;
import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.entity.PolicyDetails;
import com.cg.onlineinsurance.repository.IPolicyDetailsRepository;
import com.cg.onlineinsurance.service.ICustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired 
	ICustomerService customerService;
	
	@Autowired
	IPolicyDetailsRepository policyDetailsRepository;
	
	
	@PostMapping("/registerCustomer")
	public ResponseEntity<String> registerCustomer(@Valid @RequestBody Customer customer)
	{
		customerService.registerCustomer(customer);
		return new ResponseEntity<>("Successfully registered", HttpStatus.OK);
	}
	
	@PostMapping("/addCustomerDetail")
	public ResponseEntity<String> addCustomerDetail(@Valid @RequestBody CustomerDetailsDTO customerDetailsDTO)
	{
		customerService.addCustomerDetails(customerDetailsDTO);
		return new ResponseEntity<>("Successfully added customer details", HttpStatus.OK);
	}
	
	
	@GetMapping("/viewAllPolicies")
	public ResponseEntity<List<Policy>> viewAll()
	{   List<Policy> policyList= customerService.viewAllPolicies();
	    return new ResponseEntity<>(policyList,HttpStatus.OK) ;
	    
	}

	@PutMapping("/renewPolicy")
	public ResponseEntity<String> renewPolicy(@Valid @RequestBody PolicyDetailsDTO policydetailsDTO)
	{
		customerService.renewPolicy(policydetailsDTO);
		 return new ResponseEntity<>("Policy renewed",HttpStatus.OK) ;
		
	}
	
	@PostMapping("/buyPolicy")
	public ResponseEntity<String> buyPolicy(@RequestBody PolicyDetailsDTO policyDetailsDto )
	{   customerService.buyPolicy(policyDetailsDto);
	    
	    return new ResponseEntity<>("policy bought",HttpStatus.OK) ;
	    
	}
	
	@DeleteMapping("/removePolicyDetails")
	public ResponseEntity<String> removePolicyDetails(@RequestBody PolicyDetailsDTO policydetailsDTO)
	{
		customerService.removeCustomerPolicy(policydetailsDTO);
		return new ResponseEntity<>("policy successfully dropped",HttpStatus.OK) ;
	}

	
	
	
	@PostMapping("/userLogin")
	public ResponseEntity<String> getUserById(@RequestBody CustomerDTO customerDTO)
	{
		boolean loginSuccess=customerService.customerLogin(customerDTO);
		String result="successfully logged in";
		String failed="login failed";
		if(loginSuccess)
		{
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(failed,HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/getPolicyById/{code}")
	public ResponseEntity<Policy> viewCustomerPolicyById(@PathVariable int code) {
		 Policy policy=customerService.viewPolicyById(code);

		return new ResponseEntity<>(policy, HttpStatus.OK);
	}
	@GetMapping("/getAllPolicydetails/{id}")
	public ResponseEntity<List<PolicyDetails>> getid(@PathVariable int id)
	{   List<PolicyDetails> policyList= customerService.getpolicyDetailsById(id);
	    return new ResponseEntity<>(policyList,HttpStatus.OK) ;
	    
	}
}
