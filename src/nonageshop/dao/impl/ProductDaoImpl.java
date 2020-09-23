package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageshop.dao.ProductDao;
import nonageshop.dto.Product;

public class ProductDaoImpl implements ProductDao {
    private static final ProductDaoImpl instance = new ProductDaoImpl();
    private Connection con;

    private ProductDaoImpl() {
        // TODO Auto-generated constructor stub
    }

    public static ProductDaoImpl getInstance() {
        return instance;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    @Override
    public ArrayList<Product> listNewProduct() {
        String sql = "SELECT NO, NAME, SALEPRICE, IMAGE FROM NEW_PRO_VIEW";
        try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                ArrayList<Product> list = new ArrayList<>();
                do {
                    list.add(getNewBestProduct(rs));
                } while (rs.next());
                return list;
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Product> listBestProduct() {
        String sql = "SELECT NO, NAME, SALEPRICE, IMAGE FROM BEST_PRO_VIEW";
        try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                ArrayList<Product> list = new ArrayList<>();
                do {
                    list.add(getNewBestProduct(rs));
                } while (rs.next());
                return list;
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    @Override
    public Product getProduct(int no) {
        String sql = "SELECT NO, NAME, KIND, PRICE, SALEPRICE, MARGIN, CONTENT, IMAGE, DEL_YN, BEST_YN, REG_DATE "
                + "FROM PRODUCT " + "WHERE NO=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, no);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getProduct(rs);
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Product> listKindProduct(String kind) {
        String sql = "SELECT NO, NAME, KIND, PRICE, SALEPRICE, MARGIN, CONTENT, IMAGE, DEL_YN, BEST_YN, REG_DATE "
                + "  FROM PRODUCT " + " WHERE kind=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, kind);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ArrayList<Product> list = new ArrayList<>();
                    do {
                        list.add(getProduct(rs));
                    } while (rs.next());
                    return list;
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    @Override
    public int totalRecord(String productName) {
        int total_pages = 0;
        String sql = "select count(*) from product where name like ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            if (productName.equals("")) {
                pstmt.setString(1, "%%");
            } else {
                pstmt.setString(1, "%" + productName + "%");
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    total_pages = rs.getInt(1); // 레코드의 개수
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return total_pages;
    }

    private static int VIEW_ROWS = 5; // 페이지의 개수
    private static int COUNTS = 5; // 한 페이지에 나타낼 상품의 개수

    @Override
    public String pageNumber(int tpage, String name) {
        String str = "";

        int total_pages = totalRecord(name);            //총레코드수
        int page_count = (total_pages / COUNTS) + 1;    //총 페이지 개수 11/5 = 2.2(2) => 3 페이지로 표시+1함
        
        if (total_pages % COUNTS == 0) {                //정확히 레코드수가 5의 배수면 전체페이지수를 1감소
            page_count--;
        }
        
        if (tpage < 1) {                                //현재 보고 있는 페이지는 1미만은 없음
            tpage = 1;
        }
        
        int start_page = tpage - (tpage % VIEW_ROWS) + 1; 
        //현재 페이지가 1이면 1-(1%5) + 1 = 1, 2,3,4,5
        //현재 페이지가 2이면 2-(2%5) + 1 = 1, 
        //현재 페이지가 3이면 3-(3%5) + 1 = 1, 
        //현재 페이지가 4이면 4-(4%5) + 1 = 1, 
        //현재 페이지가 5이면 5-(5%5) + 1 = 6, 
        //현재 페이지가 6이면 6-(6%5) + 1 = 6,
        //현재 페이지가 7이면 7-(7%5) + 1 = 6,
        //현재 페이지가 8이면 8-(8%5) + 1 = 6,
        //현재 페이지가 9이면 9-(9%5) + 1 = 6,
        //현재 페이지가 10이면 10-(10%5) + 1 = 11,
        //현재 페이지가 11이면 11-(11%5) + 1 = 11,
        int end_page = start_page + (COUNTS-1); 
        //start페이지가 1이면 마지막 페이지는 5
        //start페이지가 2이면 마지막 페이지는 6
        
        if (end_page > page_count) { //마지막 페이지가 총페이지수보다 크다면 마지막 페이지를 총페이지수에 맞춤
            end_page = page_count; 
        }
        
        if (start_page > VIEW_ROWS) { //시작 페이지가 5보다 크다면 6일경우 "<< < " 표시   
            str += "<a href='adminProductList.do?tpage=1&key=" + name + "'>&lt;&lt;</a>&nbsp;&nbsp;";
            str += "<a href='adminProductList.do?tpage=" + (start_page - 1) + "&key=<%=product_name%>'>&lt;</a>&nbsp;&nbsp;";
        }
        
        // [6] [7] [8] [9] [10]
        for (int i = start_page; i <= end_page; i++) {
            if (i == tpage) {
                str += "<font color=red>[" + i + "]&nbsp;&nbsp;</font>";
            } else {
                str += "<a href='adminProductList.do?tpage=" + i + "&key=" + name + "'>[" + i + "]</a>&nbsp;&nbsp;";
            }
        }
        
        //마지막 페이지 보다 총페이지수가 크다면 뒤페이지가 있다면 " > >>" 추가
        if (page_count > end_page) {
            str += "<a href='adminProductList.do?tpage=" + (end_page + 1) + "&key=" + name + "'> &gt; </a>&nbsp;&nbsp;";
            str += "<a href='adminProductList.do?tpage=" + page_count + "&key=" + name + "'> &gt; &gt; </a>&nbsp;&nbsp;";
        }
        
        return str;
    }
    private Product getNewBestProduct(ResultSet rs) throws SQLException {
        int no = rs.getInt("NO");
        String name = rs.getString("NAME");
        Product product = new Product(no, name);
        int salePrice = rs.getInt("SALEPRICE");
        product.setSalePrice(salePrice);
        
        try {
        	String image = rs.getString("IMAGE");
        	product.setImage(image);
        }catch(SQLException e) {}
        
        return product;
    }
    
    private Product getProduct(ResultSet rs) throws SQLException {
        Product pdt = getNewBestProduct(rs);// NO, NAME, SALEPRICE, IMAGE
        pdt.setPrice(rs.getInt("PRICE"));
        pdt.setDelYn(rs.getString("DEL_YN"));
        pdt.setBestYn(rs.getString("BEST_YN"));
        pdt.setRegDate(rs.getDate("REG_DATE"));
        
        try {pdt.setContent(rs.getString("CONTENT"));}catch(SQLException e) {}
        try {pdt.setKind(rs.getString("KIND"));      }catch(SQLException e) {}
        try {pdt.setMargin(rs.getInt("MARGIN"));     }catch(SQLException e) {}
        
        return pdt;
    }
    
    @Override
    public ArrayList<Product> listProduct(int tpage, String product_name) {
        String sql = "SELECT NO, REG_DATE, NAME, PRICE, SALEPRICE , DEL_YN, BEST_YN "
                   + "  FROM PRODUCT "
                   + " WHERE NAME LIKE ? ORDER BY NO DESC";
        
        int absolutepage = 1;
        absolutepage = (tpage - 1) * COUNTS + 1;
        ArrayList<Product> productList = new ArrayList<Product>();

        try (PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
                pstmt.setString(1, "%"+product_name+"%");
            try (ResultSet rs = pstmt.executeQuery()) {
                
                if (rs.next()) {
                	rs.absolute(absolutepage);
                    int count = 0;
                    while(count < COUNTS) {
                        productList.add(getProduct(rs));
                        if (rs.isLast()) break;
                        rs.next();
                        count++;
                    }
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return productList;
    }

    @Override
    public int insertProduct(Product product) {
        String sql = "INSERT INTO PRODUCT(NAME, KIND, PRICE, SALEPRICE, MARGIN, CONTENT, IMAGE) "
                   + "VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getKind());
            pstmt.setInt(3, product.getPrice());
            pstmt.setInt(4, product.getSalePrice());
            pstmt.setInt(5, product.getMargin());
            pstmt.setString(6, product.getContent());
            pstmt.setString(7, product.getImage());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

    @Override
    public int updateProduct(Product product) {
        String sql = "UPDATE PRODUCT "
                   + "   SET KIND=?, DEL_YN=?, NAME=?, PRICE=?, SALEPRICE=?, MARGIN =?, "
                   + "       CONTENT=?, IMAGE=?, BEST_YN=? "
                   + " WHERE NO=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, product.getKind());
            pstmt.setString(2, product.getDelYn());
            pstmt.setString(3, product.getName());
            pstmt.setInt(4, product.getPrice());
            pstmt.setInt(5, product.getSalePrice());
            pstmt.setInt(6, product.getMargin());
            pstmt.setString(7, product.getContent());
            pstmt.setString(8, product.getImage());
            pstmt.setString(9, product.getBestYn());
            pstmt.setInt(10, product.getNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

}
