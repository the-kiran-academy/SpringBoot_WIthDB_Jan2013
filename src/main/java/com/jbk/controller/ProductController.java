package com.jbk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.entity.Category;
import com.jbk.entity.Product;
import com.jbk.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	
	@PostMapping(value = "/save-product")
	public ResponseEntity<Boolean> addCategory(@RequestBody Product product){
		Boolean isAdded = service.addProduct(product);
		if(isAdded) {
			return new ResponseEntity<>(isAdded,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Boolean>(isAdded,HttpStatus.ALREADY_REPORTED);
		}
	}
	
	@GetMapping(value = "/get-product-by-id/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id){
		Product product = service.getProductById(id);
		if(product!=null) {
			return new ResponseEntity<Product>(product,HttpStatus.FOUND);
		}else {
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/get-all-products")
	public ResponseEntity<List<Product>> getAllProduct(){
		List<Product> list = service.getAllProducts();
		if(!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list,HttpStatus.FOUND);
		}else {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
	}
	

}
