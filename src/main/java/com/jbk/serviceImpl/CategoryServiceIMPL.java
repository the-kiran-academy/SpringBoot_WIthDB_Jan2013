package com.jbk.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.CategoryDao;
import com.jbk.entity.Category;
import com.jbk.service.CategoryService;

@Service
public class CategoryServiceIMPL implements CategoryService{
	
	@Autowired
	private CategoryDao dao;

	@Override
	public Boolean addCategory(Category category) {
		
		return dao.addCategory(category);
	}

	@Override
	public Category getCategoryById(Long id) {
		return dao.getCategoryById(id);
	}

	@Override
	public Category getCategoryByName(String name) {
		return dao.getCategoryByName(name);
	}

	@Override
	public List<Category> getAllCAtegory() {
		return dao.getAllCAtegory();
	}

}
