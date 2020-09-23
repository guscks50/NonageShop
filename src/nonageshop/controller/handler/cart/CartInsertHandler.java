package nonageshop.controller.handler.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.dto.Product;
import nonageshop.service.CartService;

public class CartInsertHandler implements Command {
	private CartService service = new CartService();
	private String url = "cartList.do";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("get")) {
			return null;
		} else {
			HttpSession session = request.getSession();
			Member loginUser = (Member) session.getAttribute("loginUser");
			
			if (loginUser == null) {
				url = "loginform.do";
			} else {
				Cart cart = getCart(request, loginUser);
				System.out.println("cart > " + cart);
				service.addCart(cart);
			}
			response.sendRedirect(url);
			return null;
		}
	}

	private Cart getCart(HttpServletRequest request, Member member) {
		Cart cart = new Cart();
		cart.setMember(member);
		Product product = new Product();
		product.setNo(Integer.parseInt(request.getParameter("no")));
		cart.setProduct(product);
		cart.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		return cart;
	}

}
