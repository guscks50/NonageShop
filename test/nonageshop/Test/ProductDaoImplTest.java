package nonageshop.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nonageshop.dao.impl.ProductDaoImpl;
import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Product;

public class ProductDaoImplTest {
private Connection con;
private ProductDaoImpl dao;
	@Before
	public void setUp() throws Exception {
		con = JdbcUtil.getConnection();
		dao = ProductDaoImpl.getInstance();
		dao.setCon(con);
		
	}

	
	@Test
	public void testListNewProduct() {
		System.out.println("testListNewProduct()");
		List<Product> list = new ArrayList<Product>();
		list = dao.listNewProduct();
		Assert.assertNotNull(list);
		System.out.println(list);
	}

	@Test
	public void testListBestProduct() {
		 System.out.println("testListBestProduct()");
	        ArrayList<Product> bestPdtList = dao.listBestProduct();
	        Assert.assertNotNull(bestPdtList);
	        bestPdtList.stream().forEach(System.out::println);
	}

	@Test
	public void testGetProduct() {
		System.out.println(" testGetProduct()");
		Product pdt = dao.getProduct(1);
		Assert.assertNotNull(pdt);
		System.out.println(pdt);
	}

	@Test
	public void testListKindProduct() {
		 System.out.println("testListKindProduct()");
	        String[] kindArr = {"1", "2", "3", "4", "5"};
	        for(String kind : kindArr) {
	            ArrayList<Product> kindList = dao.listKindProduct(kind);
	            Assert.assertNotNull(kindList);
	            System.out.println(kind);
	            kindList.stream().forEach(System.out::println);
	        }
	}

}
