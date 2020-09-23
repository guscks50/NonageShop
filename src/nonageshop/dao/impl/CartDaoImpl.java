package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import nonageshop.dao.CartDao;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.dto.Product;

public class CartDaoImpl implements CartDao {
	private static final CartDaoImpl instance = new CartDaoImpl();
	private Connection con;

	private CartDaoImpl() {}

	public static CartDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public int insertCart(Cart cart) {
		String sql = "insert into cart(memberid, pno, quantity) values(?, ?, ?)";
	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, cart.getMember().getId());
	        pstmt.setInt(2, cart.getProduct().getNo());
	        pstmt.setInt(3, cart.getQuantity());
	        return pstmt.executeUpdate();
	    } catch (SQLException e) {
	        throw new CustomSQLException(e);
	    }
	}

	@Override
	public ArrayList<Cart> listCart(Member mId) {
		String sql = "SELECT NO, MEMBERID, PNO, MNAME, PNAME, QUANTITY, REG_DATE, SALEPRICE, RESULT"
				    + " FROM CART_VIEW"
				   + " WHERE MEMBERID=? ORDER BY NO DESC"; 
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, mId.getId());
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    ArrayList<Cart> list = new ArrayList<>();
                    do {
                        list.add(getCart(rs));
                    } while (rs.next());
                    return list;
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
	}

	private Cart getCart(ResultSet rs) throws SQLException {
		int no = rs.getInt("NO");
		Member member = new Member(rs.getString("MEMBERID"), rs.getString("MNAME"));
		Product product = new Product(rs.getInt("PNO"), rs.getString("PNAME"));
		product.setSalePrice(rs.getInt("SALEPRICE"));
		int quantity = rs.getInt("QUANTITY");
		Date regDate = rs.getDate("REG_DATE");
		return new Cart(no, member, product, quantity, regDate);
	}

	@Override
	public int deleteCart(int cartNo) {
		String sql = "DELETE CART WHERE NO = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setInt(1, cartNo);
	        return pstmt.executeUpdate();
	    } catch (SQLException e) {
	        throw new CustomSQLException(e);
	    }
	}

    @Override
    public int updateCartResult(Cart cart) {
        String sql = "update cart set result=2 where no=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, cart.getNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

}
