package com.jbk.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.entity.Category;
import com.jbk.entity.Product;
import com.jbk.exception.ProductAlreadyExistsException;
import com.jbk.service.ProductService;

import oracle.jrockit.jfr.openmbean.ProducerDescriptorType;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping(value = "/save-product")
	public ResponseEntity<Boolean> addCategory(@RequestBody @Valid Product product) {

		Boolean isAdded = service.addProduct(product);
		if (isAdded) {
			return new ResponseEntity<>(isAdded, HttpStatus.CREATED);
		} else {
			throw new ProductAlreadyExistsException("Product Already Exists With Name= "+product.getProductName());
		}
	}

	@GetMapping(value = "/get-product-by-id/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = service.getProductById(id);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/get-all-products")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> list = service.getAllProducts();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping(value = "/delete-product")
	public ResponseEntity<Boolean> deleteProduct(@RequestParam Long id) {
		Boolean isDeleted = service.deleteProduct(id);
		if (isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.NO_CONTENT);

		}

	}

	@PutMapping(value = "update-product")
	public ResponseEntity<Boolean> updateProduct(@RequestBody Product product) {
		Boolean isUpdated = service.updateProduct(product);
		if (isUpdated) {
			return new ResponseEntity<Boolean>(isUpdated, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(isUpdated, HttpStatus.NO_CONTENT);

		}
	}

	@GetMapping(value = "/sort-product")
	public ResponseEntity<List<Product>> sortProducts(@RequestParam String sortBy, @RequestParam String fieldName) {
		List<Product> list = service.sortProducts(sortBy, fieldName);
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/get-maxprice-products")
	public ResponseEntity<List<Product>> getMaxPriceProducts() {
		List<Product> list = service.getMaxPriceProducts();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(value = "/uploadSheet")
	public ResponseEntity<Map<String, Object>> uploadRecordsFromExcel(@RequestParam MultipartFile file) {
		Map<String, Object> map = service.uploadRecordsFromExcel(file);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}

}
