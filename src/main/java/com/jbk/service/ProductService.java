package com.jbk.service;

import java.util.List;

import com.jbk.entity.Product;

public interface ProductService {
	
	public Boolean addProduct(Product product);
	public Product getProductById(Long id);
	public List<Product> getAllProducts();
	public Boolean deleteProduct(Long id);
	public Boolean updateProduct(Product product);
	public List<Product> sortProducts(String sortBy,String fielsName);
	public Product getMaxPriceProducts();
	public Double countSumOfProductPrice();
	public Integer getTotalCountOfProducts();
	
	

}
