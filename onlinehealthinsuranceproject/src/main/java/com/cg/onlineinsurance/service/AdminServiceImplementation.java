package com.cg.onlineinsurance.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.exception.CustomerNotFoundException;
import com.cg.onlineinsurance.exception.DuplicatePolicyException;
import com.cg.onlineinsurance.exception.PolicyListEmptyException;
import com.cg.onlineinsurance.exception.PolicyNotFoundException;
import com.cg.onlineinsurance.repository.ICustomerDetailsRepository;
import com.cg.onlineinsurance.repository.ICustomerRepository;
import com.cg.onlineinsurance.repository.IPolicyRepository;

@Service
public class AdminServiceImplementation implements IAdminService {

	@Autowired
	IPolicyRepository policyRepository;
	
	@Autowired
	ICustomerDetailsRepository customerDetailsRepository;
	
	
    @Autowired
    ICustomerRepository customerRepository;
	
	@Override
	public String  addPolicy(Policy policy) throws DuplicatePolicyException {
        
		if(policy!=null)
		{
			String policyName=policy.getPolicyName();
			for(Policy policyEntry:policyRepository.findAll())
			{
				if(policyEntry.getPolicyName().equals(policyName))
				{
					throw new DuplicatePolicyException();
				}
			}
			policyRepository.save(policy);
		}
		
		return null;
		
	}


	@Override
	public Policy updatePolicy(Policy policy) throws PolicyNotFoundException {

		if(!policyRepository.findById(policy.getPolicyId()).isPresent()) throw new PolicyNotFoundException();
		return policyRepository.save(policy);
	}

	

	@Override
	public List<Policy> viewAllPolicies() throws PolicyListEmptyException{

		if(policyRepository.findAll().isEmpty())throw new PolicyListEmptyException();
		return policyRepository.findAll();
	}

	@Override
	public List<Policy> removePolicy(int id) throws PolicyNotFoundException{
		if(!policyRepository.findById(id).isPresent()) throw new PolicyNotFoundException();
		policyRepository.deleteById(id);
		return viewAllPolicies();
	}



	@Override
	public Policy getPolicyById(int id) {

	return policyRepository.getPolicyById(id);

	}
	
	@Override
	public List<Customer> viewAllCustomer() throws CustomerNotFoundException {
		if(customerRepository.findAll().isEmpty())throw new CustomerNotFoundException();
		return customerRepository.findAll();
		
	}
	
	@Override
	public Customer getCustomerById(int id) {

	return customerRepository.getCustomerById(id);

	}
	
	


}
