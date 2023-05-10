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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.entity.Category;
import com.jbk.entity.Supplier;
import com.jbk.service.CategoryService;
import com.jbk.service.SupplierService;

@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {
	@Autowired
	private SupplierService service;

	@PostMapping(value = "/save-supplier")
	public ResponseEntity<Boolean> addSupplier(@RequestBody Supplier supplier) {
		Boolean isAdded = service.addSupplier(supplier);
		if (isAdded) {
			return new ResponseEntity<>(isAdded, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.ALREADY_REPORTED);
		}
	}

	@GetMapping(value = "/get-supplier-by-id/{id}")
	public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
		Supplier supplier = service.getSupplierById(id);
		if (supplier != null) {
			return new ResponseEntity<Supplier>(supplier, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Supplier>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/get-supplier-by-name")
	public ResponseEntity<Supplier> getSupplierByName(@RequestParam String name) {
		Supplier supplier = service.getSupplierByName(name);
		if (supplier != null) {
			return new ResponseEntity<Supplier>(supplier, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Supplier>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/get-all-supplier")
	public ResponseEntity<List<Supplier>> getAllSupplier() {
		List<Supplier> list = service.getAllSupplier();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Supplier>>(list, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<List<Supplier>>(HttpStatus.NO_CONTENT);
		}
	}

}
