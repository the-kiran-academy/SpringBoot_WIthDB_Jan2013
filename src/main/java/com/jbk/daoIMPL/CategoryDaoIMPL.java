package com.jbk.daoIMPL;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.CategoryDao;
import com.jbk.entity.Category;

@Repository
public class CategoryDaoIMPL implements CategoryDao {

	@Autowired
	private SessionFactory factory;

	@Override
	public Boolean addCategory(Category category) {
		Session session = null;
		Boolean isAdded = false;
		try {
			session = factory.openSession();
			session.save(category);
			session.beginTransaction().commit();
			isAdded = true;

		} catch (PersistenceException e) {
			isAdded = false;
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return isAdded;
	}

	@Override
	public Category getCategoryById(Long id) {
		Session session = null;
		Category category = null;
		try {
			session = factory.openSession();
			category = session.get(Category.class, id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return category;
	}

	@Override
	public Category getCategoryByName(String name) {
		Session session = null;
		Category category = null;

		try {
			session = factory.openSession();
			Criteria criteria = session.createCriteria(Category.class);
			criteria.add(Restrictions.eq("categoryName", name));
			category = (Category) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return category;
	}

	@Override
	public List<Category> getAllCAtegory() {
		Session session = null;
		List<Category> list = null;
		try {
			session = factory.openSession();
			Criteria criteria = session.createCriteria(Category.class);
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

}
