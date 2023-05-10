package com.jbk.daoIMPL;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.SupplierDao;
import com.jbk.entity.Category;
import com.jbk.entity.Supplier;

@Repository
public class SupplierDaoIMPL implements SupplierDao {
	@Autowired
	private SessionFactory factory;

	@Override
	public Boolean addSupplier(Supplier supplier) {
		Session session = null;
		Boolean isAdded = false;
		try {
			session = factory.openSession();
			session.save(supplier);
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
	public Supplier getSupplierById(Long id) {
		Session session = null;
		Supplier supplier = null;
		try {
			session = factory.openSession();
			supplier = session.get(Supplier.class, id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return supplier;
	}

	@Override
	public Supplier getSupplierByName(String name) {
		Session session = null;
		Supplier supplier = null;

		try {
			session = factory.openSession();
			Criteria criteria = session.createCriteria(Supplier.class);
			criteria.add(Restrictions.eq("categoryName", name));
			supplier = (Supplier) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return supplier;
	}

	@Override
	public List<Supplier> getAllSupplier() {
		Session session = null;
		List<Supplier> list = null;
		try {
			session = factory.openSession();
			Criteria criteria = session.createCriteria(Supplier.class);
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
