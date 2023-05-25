package com.jbk.daoIMPL;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
			session = factory.openSession();
			Product product = session.get(Product.class, id);
			session.delete(product);
			session.beginTransaction().commit();
			isDeleted = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isDeleted;
	}

	@Override
	public Boolean updateProduct(Product product) {
		Session session = null;
		Boolean isUpdated = false;
		try {
			session = factory.openSession();
			session.update(product);
			session.beginTransaction().commit();
			isUpdated = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isUpdated;
	}

	@Override
	public List<Product> sortProducts(String sortBy, String fieldName) {
		Session session = null;
		List<Product> list = null;
		try {
			session = factory.openSession();
			Criteria criteria = session.createCriteria(Product.class);

			if (sortBy.equalsIgnoreCase("asc")) {
				criteria.addOrder(Order.asc(fieldName));
			} else {
				criteria.addOrder(Order.desc(fieldName));
			}

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

	public Double getMaxPrice() {
		Double maxPrice = 0d;
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.setProjection(Projections.max("productPrice"));
		List list = criteria.list();
		if (!list.isEmpty()) {
			maxPrice = (Double) list.get(0);
		}
		return maxPrice;

	}

	@Override
	public List<Product> getMaxPriceProducts() {
		Session session = null;
		List<Product> products = null;
		try {
			Double maxPrice = getMaxPrice();
			if (maxPrice > 0) {
				session = factory.openSession();
				Criteria criteria = session.createCriteria(Product.class);
				criteria.add(Restrictions.eq("productPrice", maxPrice));
				products = criteria.list();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return products;
	}

	@Override
	public Double countSumOfProductPrice() {

		return null;
	}

	@Override
	public Integer getTotalCountOfProducts() {

		return null;
	}

	@Override
	public int[] uploadProducts(List<Product> list) {
		int addCount = 0;
		int excludeCount = 0;
		int[] arr = new int[2];

		for (Product product : list) {
			Boolean isAdded = addProduct(product);
			if (isAdded) {
				addCount = addCount + 1;
			} else {
				excludeCount = excludeCount + 1;
			}
		}
		arr[0] = addCount;
		arr[1] = excludeCount;
		return arr;
	}

}
