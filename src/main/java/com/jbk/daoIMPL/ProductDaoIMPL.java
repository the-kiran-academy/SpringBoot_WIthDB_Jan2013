package com.jbk.daoIMPL;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jbk.dao.ProductDao;
import com.jbk.entity.Product;
import com.jbk.entity.Supplier;

@Repository
public class ProductDaoIMPL implements ProductDao {

	@Autowired
	private SessionFactory factory;

	@Override
	public Boolean addProduct(Product product) {
		Session session = null;
		Boolean isAdded = false;
		try {
			session = factory.openSession();
			session.save(product);
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
	public Product getProductById(Long id) {
		Session session = null;
		Product product = null;
		try {
			session = factory.openSession();
			product = session.get(Product.class, id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		Session session = null;
		List<Product> list = null;
		try {
			session = factory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
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

	@Override
	public Boolean deleteProduct(Long id) {
		Session session = null;
		Boolean isDeleted = false;
		try {
			Product product = getProductById(id);
			session.delete(product);
			session.beginTransaction().commit();
			isDeleted = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

	@Override
	public Boolean updateProduct(Product product) {

		return null;
	}

	@Override
	public List<Product> sortProducts(String sortBy, String fielsName) {

		return null;
	}

	@Override
	public Product getMaxPriceProducts() {

		return null;
	}

	@Override
	public Double countSumOfProductPrice() {

		return null;
	}

	@Override
	public Integer getTotalCountOfProducts() {

		return null;
	}

}
