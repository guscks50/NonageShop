package nonageshop.controller.handler.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.service.CartService;

public class CartDeleteHandler implements Command {
	private CartService service = new CartService();
	private String url = "cartList.do";

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("get")) {
			return url;
		} else {
			String[] noArr = request.getParameterValues("no");
			for (String no : noArr) {
				System.out.println("no >" + no);
				service.removeCart(Integer.parseInt(no));
			}
			return url;
		}

	}

}
