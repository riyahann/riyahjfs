package com.cg.onlineinsurance.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="policy")
public class Policy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int policyId;
	
	@OneToMany(mappedBy = "policy")
	private List<PolicyDetails> policiesDetails;
	
	
	@Column(name="policy_name")
	private String policyName;
	
	@Column(name="age_group")
	private int ageGroup;
	
	@Column(name="policy_term")
	private int policyTerm;
	
	@Column(name="base_amount")
	private double baseAmount;
	
	@Column(name="policy_cover")
	private double policyCover;

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}


	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public int getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(int ageGroup) {
		this.ageGroup = ageGroup;
	}

	public int getPolicyTerm() {
		return policyTerm;
	}

	public void setPolicyTerm(int policyTerm) {
		this.policyTerm = policyTerm;
	}

	public double getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(double baseAmount) {
		this.baseAmount = baseAmount;
	}

	public double getPolicyCover() {
		return policyCover;
	}

	public void setPolicyCover(double policyCover) {
		this.policyCover = policyCover;
	}

	public Policy(int policyId, String policyName, int ageGroup,
			int policyTerm, double baseAmount, double policyCover) {
		super();
		this.policyId = policyId;
		this.policyName = policyName;
		this.ageGroup = ageGroup;
		this.policyTerm = policyTerm;
		this.baseAmount = baseAmount;
		this.policyCover = policyCover;
	}

	public Policy() {
		super();
	}

	

	
	
}