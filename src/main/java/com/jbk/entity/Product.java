package com.jbk.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.jbk.annotation.AlphaNumeric;


@Entity
public class Product {
	
	@Id
	@Column(nullable = false,unique = true)
	private Long productId;
	
	@NotEmpty
	@AlphaNumeric
	@Column(nullable = false,unique = true)
	private String productName;
	
	@OneToOne
	@JoinColumn(name = "supplierid")
	private Supplier supplierId;
	
	@OneToOne
	@JoinColumn(name = "categoryid")
	private Category categoryId;
	
	@Min(1)
	@Column(nullable = false)
	private int productQTY;
	
	@Min(1)
	@Column(nullable = false)
	private Double productPrice;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(Long productId, String productName, Supplier supplierId, Category categoryId, int productQTY,
			Double productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.supplierId = supplierId;
		this.categoryId = categoryId;
		this.productQTY = productQTY;
		this.productPrice = productPrice;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Supplier getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Supplier supplierId) {
		this.supplierId = supplierId;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	public int getProductQTY() {
		return productQTY;
	}

	public void setProductQTY(int productQTY) {
		this.productQTY = productQTY;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", supplierId=" + supplierId
				+ ", categoryId=" + categoryId + ", productQTY=" + productQTY + ", productPrice=" + productPrice + "]";
	}
	
	

}
