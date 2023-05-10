package com.jbk.dao;

import java.util.List;

import com.jbk.entity.Supplier;


public interface SupplierDao {
	
	public Boolean addSupplier(Supplier supplier);
	public Supplier getSupplierById(Long id);
	public Supplier getSupplierByName(String name);
	public List<Supplier> getAllSupplier();

}
