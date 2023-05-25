package com.jbk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jbk.dao.ProductDao;
import com.jbk.entity.Product;
import com.jbk.utility.TestUtility;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringBootWIthDbJan2013ApplicationTests {
	
	@Autowired
	private ProductDao dao;
	@Autowired
	private TestUtility utility;

	@Test
	void tesrgetAllProducts() {
		assertEquals(true, dao.getAllProducts().size()>0);
	}

}
