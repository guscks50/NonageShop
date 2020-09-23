package nonageshop.controller.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.service.CartService;

public class CartListHandler implements Command {
	private CartService service = new CartService();
	private String url;

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) {
			url = "loginform.do";
		} else {
			ArrayList<Cart> cartList = service.listCartByMember(loginUser);
			int totalPrice = 0;
			if (cartList != null) {
				for (Cart cart : cartList) {
					totalPrice += cart.getProduct().getSalePrice() * cart.getQuantity();
				}
			}else {
				cartList = new ArrayList<Cart>();
			}
			request.setAttribute("cartList", cartList);
			request.setAttribute("totalPrice", totalPrice);
			url = "mypage/cartList.jsp";
		}
		return url;
	}

}
