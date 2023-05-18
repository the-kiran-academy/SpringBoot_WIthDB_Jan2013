package com.jbk.dao;

import java.util.List;

import com.jbk.entity.Product;

public interface ProductDao {
	public Boolean addProduct(Product product);
	public Product getProductById(Long id);
	public List<Product> getAllProducts();
	public Boolean deleteProduct(Long id);
	public Boolean updateProduct(Product product);
	public List<Product> sortProducts(String sortBy,String fielsName);
	public List<Product> getMaxPriceProducts();
	public Double countSumOfProductPrice();
	public Integer getTotalCountOfProducts();
	public String uploadProducts(List<Product> list);
}
