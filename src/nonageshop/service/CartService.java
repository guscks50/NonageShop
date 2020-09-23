package nonageshop.service;

import java.sql.Connection;
import java.util.ArrayList;

import nonageshop.dao.impl.CartDaoImpl;
import nonageshop.ds.JndiDS;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;

public class CartService {
	private Connection con = JndiDS.getConnection();
//	private Connection con = JdbcUtil.getConnection();
    private CartDaoImpl dao = CartDaoImpl.getInstance();
    
    public CartService() {
        dao.setCon(con);
    }
    
    public int addCart(Cart cart) {
    	return dao.insertCart(cart);
    }
    
    public int removeCart(int cartNo) {
    	return dao.deleteCart(cartNo);
    }
    
    public ArrayList<Cart> listCartByMember(Member member){
    	return dao.listCart(member);
    }
}
