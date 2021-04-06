package com.cg.onlineinsurance;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.onlineinsurance.entity.Policy;
import com.cg.onlineinsurance.repository.IPolicyRepository;
import com.cg.onlineinsurance.service.AdminServiceImplementation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTest {
	@InjectMocks
	AdminServiceImplementation adminService;
	@Mock
	IPolicyRepository policyRepository;
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	public void testGetAllItems() {
		List<Policy> policyList=new ArrayList<Policy>();
		policyList.add(new Policy(1,"policy-type1",30,2,3000,2000000));
		policyList.add(new Policy(2,"policy-type2",40,5,5000,5000000));
		policyList.add(new Policy(3,"policy-type3",50,4,6000,9000000));
		Mockito.when(policyRepository.findAll()).thenReturn(policyList);
		List<Policy> list=adminService.viewAllPolicies();
		assertEquals(3,list.size());
		Mockito.verify(policyRepository,Mockito.times(2)).findAll();
	}
	
	@Test
	public void getPolicyById() {
		 Mockito.when(policyRepository.getPolicyById(1)).thenReturn(new Policy(1,"policy-type1",30,2,300,200000));
         
	       Policy emp = adminService.getPolicyById(1);
	         
	        assertEquals("policy-type1",emp.getPolicyName());
	        assertEquals(30,emp.getAgeGroup());
	        assertEquals(2,emp.getPolicyTerm());
	        assertEquals(300, emp.getBaseAmount(),0.001);
	        assertEquals(200000, emp.getPolicyCover(),0.001);
	    }

		   }
	  
   
   


