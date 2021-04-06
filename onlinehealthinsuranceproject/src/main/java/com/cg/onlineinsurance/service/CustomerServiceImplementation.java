package com.cg.onlineinsurance.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineinsurance.dto.CustomerDTO;
import com.cg.onlineinsurance.dto.CustomerDetailsDTO;
import com.cg.onlineinsurance.dto.PolicyDetailsDTO;
import com.cg.onlineinsurance.entity.Customer;
import com.cg.onlineinsurance.entity.CustomerDetails;
import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.entity.PolicyDetails;
import com.cg.onlineinsurance.exception.CustomerNotFoundException;
import com.cg.onlineinsurance.exception.DuplicateCustomerDetailException;
import com.cg.onlineinsurance.exception.DuplicateCustomerException;
import com.cg.onlineinsurance.exception.DuplicateCustomerPolicyException;
import com.cg.onlineinsurance.exception.PolicyActiveException;
import com.cg.onlineinsurance.exception.PolicyListEmptyException;
import com.cg.onlineinsurance.exception.PolicyNotFoundException;
import com.cg.onlineinsurance.repository.ICustomerDetailsRepository;
import com.cg.onlineinsurance.repository.ICustomerRepository;
import com.cg.onlineinsurance.repository.IPolicyDetailsRepository;
import com.cg.onlineinsurance.repository.IPolicyRepository;


@Service

public class CustomerServiceImplementation implements ICustomerService {

	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	ICustomerDetailsRepository customerDetailsRepository;
	
	@Autowired
	IPolicyRepository policyRepository;
	
	@Autowired
	IPolicyDetailsRepository policyDetailsRepository;
	
	@Autowired
	ICustomerService customerservice;

	
	@Override
	public Customer registerCustomer(Customer customer) throws DuplicateCustomerException {
		
		for(Customer customerdetail:customerRepository.findAll())
		{
			if(customerdetail.getEmailId().equals(customer.getEmailId()))
			{
				throw new DuplicateCustomerException();
			}
		}
	
		return customerRepository.save(customer);
		
	}


	@Override
	public String addCustomerDetails(CustomerDetailsDTO customerDetailsDTO) throws DuplicateCustomerException,DuplicateCustomerDetailException{
		
		if(customerDetailsDTO!=null)
		{    
			if(!customerRepository.existsById(customerDetailsDTO.getCustomerId()))
			{
				throw new CustomerNotFoundException();
			}
			
			for(CustomerDetails customerDetail: customerDetailsRepository.findAll())
			{
				if(customerDetail.getCustomer().getCustomerId()==customerDetailsDTO.getCustomerId())
				{
					throw new DuplicateCustomerDetailException();
				}
			}
			CustomerDetails customerDetails=new CustomerDetails();
			customerDetails.setCustomerDetailId(customerDetailsDTO.getCustomerDetailId());//
			customerDetails.setIsAlcoholic(customerDetailsDTO.getIsAlcoholic());
			customerDetails.setBodyMassIndex(customerDetailsDTO.getBodyMassIndex());
			customerDetails.setIsDiabetic(customerDetailsDTO.getIsDiabetic());
			customerDetails.setIsSmoker(customerDetailsDTO.getIsSmoker());
			customerDetails.setSalaryBracket(customerDetailsDTO.getSalaryBracket());
			customerDetails.setAge(customerDetailsDTO.getAge());
			
			
			Customer customer=customerRepository.getCustomerById(customerDetailsDTO.getCustomerId());
			customerDetails.setCustomer(customer);
			customerDetailsRepository.save(customerDetails);
		}
		
		return null;
	}




	@Override
	public Customer getCustomerById(int id) {
		return customerRepository.getCustomerById(id);
	}


	
	@Override
	public List<Policy> viewAllPolicies() throws PolicyListEmptyException {
		if(policyRepository.findAll().isEmpty())throw new PolicyListEmptyException();
		return policyRepository.findAll();
	}



	@Override
	public String buyPolicy(PolicyDetailsDTO policydetailsDto) throws DuplicateCustomerPolicyException,PolicyNotFoundException {
        
	if(policydetailsDto!=null) {
		int custId=policydetailsDto.getCustomerId();
		 int policyId=policydetailsDto.getPolicyId();
		 List<PolicyDetails> policydetailList=policyDetailsRepository.findAll();
		 for(PolicyDetails policydetail:policydetailList)
		 {
			 if(policydetail.getCustomer().getCustomerId()==custId && policydetail.getPolicy().getPolicyId()==policyId)
	    	{
	    		throw new DuplicateCustomerPolicyException();
	    	}
		 }
		 
		 if( policyRepository.getPolicyById(policyId)==null)
		 {
	    		throw new PolicyNotFoundException();
		 }
			
		Policy policy=policyRepository.getPolicyById(policydetailsDto.getPolicyId());
		PolicyDetails policyDetails=new PolicyDetails();
		Customer customer=customerRepository.getCustomerById(policydetailsDto.getCustomerId());

		LocalDate todaysDate = LocalDate.now();
		int term=policy.getPolicyTerm();
		int code=policydetailsDto.getCustomerId();
		int age=0;
		List<CustomerDetails> details1=customerDetailsRepository.findAll();
		for(CustomerDetails det:details1)
		{
			if(det.getCustomer().getCustomerId()==code)
			{
				age=det.getAge();
				break;
			}
		}
		
		policyDetails.setPolicyDetailsId(policydetailsDto.getPolicyDetailsId());
		policyDetails.setStartDate(todaysDate);
		policyDetails.setExpiryDate(todaysDate.plusYears(term));
		
		policyDetails.setPremiumAmounts(calculatePremium(age,policy));
		policyDetails.setStatus(false);
		policyDetails.setPolicy(policy);
		policyDetails.setCustomer(customer);
		

		policyDetailsRepository.save(policyDetails);
	}
		return null;
	}


	@Override
	public String removeCustomerPolicy(PolicyDetailsDTO policyDetailsDTO)throws PolicyNotFoundException {
		
			 
			 Policy policy=policyRepository.getPolicyById(policyDetailsDTO.getPolicyId());
			 Customer customer=customerRepository.getCustomerById(policyDetailsDTO.getCustomerId());
			 List<PolicyDetails> policydetails=policyDetailsRepository.findAll();
			 for(PolicyDetails pol: policydetails)
			 {
				 if((pol.getPolicy().equals(policy))  && (pol.getCustomer().equals(customer)))
				 {
					 policyDetailsRepository.delete(pol);
				 }
			 }
			 return null;
			
	}
	

	@Override
	public String renewPolicy(PolicyDetailsDTO policydetailsDTO) throws PolicyActiveException{
		
		  
			Policy policy=policyRepository.getPolicyById(policydetailsDTO.getPolicyId());
		if(policydetailsDTO.isStatus()) {
			throw new PolicyActiveException();
		}
			int id =policydetailsDTO.getPolicyDetailsId();
			
	        
			PolicyDetails policyDetails=policyDetailsRepository.findById(id).get();
	        
			Customer customer=customerRepository.getCustomerById(policydetailsDTO.getCustomerId());
			LocalDate todaysDate = LocalDate.now();
		int code=policydetailsDTO.getCustomerId();
		int age=0;
		List<CustomerDetails> details1=customerDetailsRepository.findAll();
		for(CustomerDetails det:details1)
		{
			if(det.getCustomer().getCustomerId()==code)
			{
				age=det.getAge();
				break;
			}
		}
		
		
			int term=policy.getPolicyTerm();
			policyDetails.setPolicyDetailsId(policydetailsDTO.getPolicyDetailsId());
			policyDetails.setStartDate(todaysDate);
			policyDetails.setExpiryDate(todaysDate.plusYears(term));
			policyDetails.setStatus(true);
			policyDetails.setPremiumAmounts(calculatePremium(age,policy));
			policyDetails.setCustomer(customer);
			policyDetails.setPolicy(policy);
			policyDetailsRepository.save(policyDetails);
	        
			return null;
	}



	@Override
	public boolean customerLogin(CustomerDTO customerDto) {
		String email=customerDto.getEmail();
		String password=customerDto.getPassword();
		for(Customer customer:customerRepository.findAll())
		{
			if(customer.getEmailId().equals(email) && customer.getPassword().equals(password))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public double calculatePremium(int age,Policy policy)
	{
		
		double amount=policy.getBaseAmount();
		
		if(age<=25 && age>=1) {
			
		amount=amount*3;
	
		}
		else if(age>25 && age<=35)
		{
			amount=amount*5;
		}
		
		else if(age>35 &&age<=45)
		{
			amount=amount*10;
			
		}
		
		else if(age>45 &&age<100)
		{
		amount=amount*15;
		}
		
		return amount;
	}

	@Override
	public Policy viewPolicyById(int id) {
	
	return policyRepository.getPolicyById(id);
	
	}

	@Override
	public List<PolicyDetails>  getpolicyDetailsById(int id) {
		
		return policyDetailsRepository.getPolicyDetailsById(id);
	}

}


	


	
	


