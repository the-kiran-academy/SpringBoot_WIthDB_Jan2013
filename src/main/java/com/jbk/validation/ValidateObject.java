package com.jbk.validation;

import java.util.HashMap;
import java.util.Map;

import com.jbk.entity.Product;

public class ValidateObject {
	public static Map<String, String> map = null;

	public static Map<String, String> validateProduct(Product product) {

		map = new HashMap<>();

		if (product.getProductName() != null) {
			boolean isValidated = true;

			try {
				Double.parseDouble(product.getProductName());
			} catch (NumberFormatException e) {
				isValidated = false;
			}
			if (isValidated) {
				map.put("productName", "Invalid Product Name");

			}
		}else {
			map.put("productName", "ProductName is required");
		}

		if (product.getProductQTY() <= 0) {

			map.put("productQty", "ProductQty should be greater than 0");
		}
		if (product.getProductPrice() <= 0) {

			map.put("productPrice", "ProductPrice should be greater than 0");
		}

		if (product.getSupplierId().getSupplierId() <= 0) {

			map.put("SupplierId", "SupplierId should be greater than 0");
		}

		if (product.getCategoryId().getCategoryId() <= 0) {

			map.put("CategoryId", "CategoryId should be greater than 0");
		}

		return map;

	}

}
