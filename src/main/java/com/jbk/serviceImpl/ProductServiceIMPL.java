package com.jbk.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.dao.ProductDao;
import com.jbk.entity.Category;
import com.jbk.entity.Product;
import com.jbk.entity.Supplier;
import com.jbk.service.ProductService;

@Service
public class ProductServiceIMPL implements ProductService {

	@Autowired
	private ProductDao dao;

	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public Boolean addProduct(Product product) {

		String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
		product.setProductId(Long.parseLong(id));
		return dao.addProduct(product);
	}

	@Override
	public Product getProductById(Long id) {
		return dao.getProductById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return dao.getAllProducts();
	}

	@Override
	public Boolean deleteProduct(Long id) {
		return dao.deleteProduct(id);
	}

	@Override
	public Boolean updateProduct(Product product) {
		return dao.updateProduct(product);
	}

	@Override
	public List<Product> sortProducts(String sortBy, String fieldName) {
		return dao.sortProducts(sortBy, fieldName);
	}

	@Override
	public List<Product> getMaxPriceProducts() {
		return dao.getMaxPriceProducts();
	}

	@Override
	public Double countSumOfProductPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalCountOfProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Product> readExcel(String filePath) {
		List<Product> list = new ArrayList<Product>();
		try {
			FileInputStream fis = new FileInputStream(new File(filePath));

			Workbook workbook = new XSSFWorkbook(fis);

			Sheet sheet = workbook.getSheet("product");

			Iterator<Row> rows = sheet.rowIterator();

			Product product = null;
			int count = 1;
			while (rows.hasNext()) {
				Row row = (Row) rows.next();

				if (row.getRowNum() == 0) {
					continue;
				}

				product = new Product();
				Long id = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date()));
				id = id + count;
				product.setProductId(id);
				count = count + 1;
				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {
					Cell cell = (Cell) cells.next();
					int columnIndex = cell.getColumnIndex();

					switch (columnIndex) {
					case 0: {

						product.setProductName(cell.getStringCellValue());
						break;
					}

					case 1: {
						Supplier supplier = new Supplier();
						supplier.setSupplierId((long) cell.getNumericCellValue());
						product.setSupplierId(supplier);
						break;
					}

					case 2: {
						Category category = new Category();
						category.setCategoryId((long) cell.getNumericCellValue());
						product.setCategoryId(category);
						break;
					}

					case 3: {
						product.setProductQTY((int) cell.getNumericCellValue());
						break;
					}

					case 4: {
						product.setProductPrice(cell.getNumericCellValue());
						break;
					}

					}

				}
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public String uploadRecordsFromExcel(MultipartFile file) {

		String fileName = file.getOriginalFilename();
		String filePath = "src/main/resources";
		List<Product> list = new ArrayList<Product>();
		String msg = null;
		try {
			byte[] data = file.getBytes();
			FileOutputStream fos = new FileOutputStream(new File(filePath + File.separator + fileName));

			fos.write(data);

			list = readExcel(filePath + File.separator + fileName);

			msg = dao.uploadProducts(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return msg;
	}

}
