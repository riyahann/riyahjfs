package com.cg.onlineinsurance.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.onlineinsurance.exception.CustomerNotFoundException;
import com.cg.onlineinsurance.exception.CustomerPolicyNotFoundException;
import com.cg.onlineinsurance.exception.DuplicateCustomerException;
import com.cg.onlineinsurance.exception.DuplicateCustomerPolicyException;
import com.cg.onlineinsurance.exception.DuplicatePolicyException;
import com.cg.onlineinsurance.exception.EmptyPolicyException;
import com.cg.onlineinsurance.exception.PolicyListEmptyException;
import com.cg.onlineinsurance.exception.PolicyNotFoundException;
import com.cg.onlineinsurance.exception.DuplicateCustomerDetailException;
import com.cg.onlineinsurance.exception.PolicyActiveException;



@ControllerAdvice
public class ExceptionsHandler {
	
	
	@ExceptionHandler(value=DuplicatePolicyException.class)
	public ResponseEntity<Object> exception( DuplicatePolicyException exception){
		return new ResponseEntity<>("Policy already exists",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=EmptyPolicyException.class)
	public ResponseEntity<Object> exception(EmptyPolicyException exception){
		return new ResponseEntity<>("cannnot add an empty policy",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=PolicyNotFoundException.class)
	public ResponseEntity<Object> exception(PolicyNotFoundException exception){
		return new ResponseEntity<>("Policy not found",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=PolicyListEmptyException.class)
	public ResponseEntity<Object> exception(PolicyListEmptyException exception){
		return new ResponseEntity<>("Policy List is Empty",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=CustomerNotFoundException.class)
	public ResponseEntity<Object> exception(CustomerNotFoundException exception){
		return new ResponseEntity<>("Could not find any registered customer by this credential",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=DuplicateCustomerException.class)
	public ResponseEntity<Object> exception(DuplicateCustomerException exception){
		return new ResponseEntity<>("Customer already exists",HttpStatus.NOT_FOUND);
		}
	@ExceptionHandler(value=DuplicateCustomerPolicyException.class)
	public ResponseEntity<Object> exception(DuplicateCustomerPolicyException exception){
		return new ResponseEntity<>("This policy has already been bought ",HttpStatus.NOT_FOUND);
		}
	@ExceptionHandler(value=CustomerPolicyNotFoundException.class)
	public ResponseEntity<Object> exception(CustomerPolicyNotFoundException exception){
		return new ResponseEntity<>("Invalid Customer's policy Details",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=PolicyActiveException.class)
	public ResponseEntity<Object> exception(PolicyActiveException exception){
		return new ResponseEntity<>("Cannot renew an active policy",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=DuplicateCustomerDetailException.class)
	public ResponseEntity<Object> exception(DuplicateCustomerDetailException exception){
		return new ResponseEntity<>("Details for this customer already exists",HttpStatus.NOT_FOUND);
		}
	
	
}



