package com.cg.onlineinsurance.service;

import java.util.List;
import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.exception.CustomerNotFoundException;
import com.cg.onlineinsurance.exception.DuplicatePolicyException;
import com.cg.onlineinsurance.exception.PolicyListEmptyException;
import com.cg.onlineinsurance.exception.PolicyNotFoundException;

public interface IAdminService {


	String   addPolicy(Policy policy) throws DuplicatePolicyException;
	List<Policy> removePolicy(int id) throws PolicyNotFoundException;
	Policy updatePolicy(Policy policy) throws PolicyNotFoundException;
	List<Policy> viewAllPolicies() throws PolicyListEmptyException;
	
	Policy getPolicyById(int id);
	List<Customer> viewAllCustomer() throws CustomerNotFoundException;
	Customer getCustomerById(int code);
	
}
