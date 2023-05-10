package com.jbk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

@Entity
public class Supplier{
	
	
	@Id
	@Column(nullable = false,unique = true)
	private Long supplierId;
	
	@Column(nullable = false,unique = true)
	private String supplierName;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private Integer postalCode;
	
	@Column(nullable = false)
	private String country;
	
	@Column(nullable = false,unique = true)
	private String mobileNo;
	
	
	public Supplier() {
		// TODO Auto-generated constructor stub
	}


	public Supplier(Long supplierId, String supplierName, String city, Integer postalCode, String country,
			String mobileNo) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
		this.mobileNo = mobileNo;
	}


	public Long getSupplierId() {
		return supplierId;
	}


	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}


	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public Integer getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	

}
