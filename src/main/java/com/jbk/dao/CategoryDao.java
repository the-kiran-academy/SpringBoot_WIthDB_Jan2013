package com.jbk.dao;

import java.util.List;

import com.jbk.entity.Category;

public interface CategoryDao {

	public Boolean addCategory(Category category);
	public Category getCategoryById(Long id);
	public Category getCategoryByName(String name);
	public List<Category> getAllCAtegory();

}
