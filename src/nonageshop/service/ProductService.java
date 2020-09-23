package nonageshop.service;

import java.sql.Connection;
import java.util.ArrayList;

import nonageshop.dao.impl.ProductDaoImpl;
import nonageshop.ds.JndiDS;
import nonageshop.dto.Product;

public class ProductService {
    private ProductDaoImpl dao = ProductDaoImpl.getInstance();
    private Connection con = JndiDS.getConnection();

    public ProductService() {
        dao.setCon(con);
    }

    // 신상품 리스트 얻어오기
    public ArrayList<Product> newProducts() {
        return dao.listNewProduct();
    }

    // 베스트 상품 리스트 얻어오기
    public ArrayList<Product> bestProducts() {
        return dao.listBestProduct();
    }

    // 상품번호로 상품 정보 한개 얻어오기
    public Product getProduct(int no) {
        return dao.getProduct(no);
    }
    
    // 상품종류별 상품 리스트 얻어오기
    public ArrayList<Product> kindProducts(String kind) {
        return dao.listKindProduct(kind);
    }
    
    //관리자용 - 상품관리 페이징
    public int totalRecord(String productName) {
    	return dao.totalRecord(productName);
    }
    public String pageNumber(int tpage, String name){
    	return dao.pageNumber(tpage, name);
    }
    public ArrayList<Product> listProduct(int tpage, String product_name){
    	return dao.listProduct(tpage, product_name);
    }
    
    //관리자용 - 상품 등록 및 수정
    public int insertProduct(Product product){
    	return dao.insertProduct(product);
    }
    
    public int updateProduct(Product product){
    	return dao.updateProduct(product);
    }
}
