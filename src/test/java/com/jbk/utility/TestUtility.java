package com.jbk.utility;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.jbk.entity.Category;
import com.jbk.entity.Product;
import com.jbk.entity.Supplier;

@Component
public class TestUtility {
	
	public Product getProduct() {
		String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
		Supplier supplier =new Supplier();
		supplier.setSupplierId(1L);
		
		Category category=new Category();
		category.setCategoryId(1L);
		Product product =new Product(Long.parseLong(id), "prd2", supplier, category, 10, 10d);
		return product;
		
	}

}
