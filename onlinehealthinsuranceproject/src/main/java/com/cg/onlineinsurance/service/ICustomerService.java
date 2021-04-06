package com.cg.onlineinsurance.service;

import java.util.List;

import com.cg.onlineinsurance.dto.CustomerDTO;
import com.cg.onlineinsurance.dto.CustomerDetailsDTO;
import com.cg.onlineinsurance.dto.PolicyDetailsDTO;
import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.entity.PolicyDetails;
import com.cg.onlineinsurance.exception.CustomerNotFoundException;
import com.cg.onlineinsurance.exception.DuplicateCustomerDetailException;
import com.cg.onlineinsurance.exception.DuplicateCustomerException;
import com.cg.onlineinsurance.exception.DuplicateCustomerPolicyException;
import com.cg.onlineinsurance.exception.PolicyActiveException;
import com.cg.onlineinsurance.exception.PolicyListEmptyException;
import com.cg.onlineinsurance.exception.PolicyNotFoundException;

public interface  ICustomerService {
	
	Customer registerCustomer(Customer customer) throws DuplicateCustomerException;
	String  addCustomerDetails(CustomerDetailsDTO customerDetails) throws CustomerNotFoundException,DuplicateCustomerDetailException;
	List<Policy> viewAllPolicies() throws PolicyListEmptyException;
	String renewPolicy(PolicyDetailsDTO policydetailsDTO ) throws PolicyActiveException;
	String buyPolicy(PolicyDetailsDTO policydetailsDto) throws DuplicateCustomerPolicyException,PolicyNotFoundException;
    String  removeCustomerPolicy(PolicyDetailsDTO policyDetailsDTO) throws PolicyNotFoundException;
  
    Customer getCustomerById(int id);
    boolean customerLogin(CustomerDTO customerDto);
    Policy viewPolicyById(int id);
    List<PolicyDetails> getpolicyDetailsById(int id);
	double calculatePremium(int age, Policy policy);
}