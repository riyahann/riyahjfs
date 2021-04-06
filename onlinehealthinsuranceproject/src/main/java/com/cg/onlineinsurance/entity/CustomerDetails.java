package com.cg.onlineinsurance.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer_details")
public class CustomerDetails {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerDetailId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Column(name="is_diabetic")
	private String isDiabetic;
	
	@Column(name="is_smoker")
	private String isSmoker;
	
	@Column(name="isAlcoholic")
	private String isAlcoholic;
	
	@Column(name="bmi")
	private double bodyMassIndex;
	
	@Column(name="age")
	private int age;
	
	@Column(name="salary_bracket")
	private double salaryBracket;

	public CustomerDetails(int customerDetailId, Customer customer, String isDiabetic, String isSmoker,
			String isAlcoholic, double bodyMassIndex, int age, double salaryBracket) {
		super();
		this.customerDetailId = customerDetailId;
		this.customer = customer;
		this.isDiabetic = isDiabetic;
		this.isSmoker = isSmoker;
		this.isAlcoholic = isAlcoholic;
		this.bodyMassIndex = bodyMassIndex;
		this.age = age;
		this.salaryBracket = salaryBracket;
	}

	public int getCustomerDetailId() {
		return customerDetailId;
	}

	public void setCustomerDetailId(int customerDetailId) {
		this.customerDetailId = customerDetailId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getIsDiabetic() {
		return isDiabetic;
	}

	public void setIsDiabetic(String isDiabetic) {
		this.isDiabetic = isDiabetic;
	}

	public String getIsSmoker() {
		return isSmoker;
	}

	public void setIsSmoker(String isSmoker) {
		this.isSmoker = isSmoker;
	}

	public String getIsAlcoholic() {
		return isAlcoholic;
	}

	public void setIsAlcoholic(String isAlcoholic) {
		this.isAlcoholic = isAlcoholic;
	}

	public double getBodyMassIndex() {
		return bodyMassIndex;
	}

	public void setBodyMassIndex(double bodyMassIndex) {
		this.bodyMassIndex = bodyMassIndex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalaryBracket() {
		return salaryBracket;
	}

	public void setSalaryBracket(double salaryBracket) {
		this.salaryBracket = salaryBracket;
	}

	public CustomerDetails() {
		super();

	}

	

	
	
	
}
