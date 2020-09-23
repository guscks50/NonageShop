package nonageshop.dao;

import java.util.ArrayList;

import nonageshop.dto.Cart;
import nonageshop.dto.Member;

public interface CartDao {
	int insertCart(Cart cart);				//장바구니에 상품 등록
	ArrayList<Cart> listCart(Member mId);	//사용자별 상품목록
	int deleteCart(int cartNo);				//장바구니의 상품 삭제	
	int updateCartResult(Cart cart);
}
