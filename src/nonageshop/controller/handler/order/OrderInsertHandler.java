package nonageshop.controller.handler.order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.dto.OrderDetail;
import nonageshop.dto.Orders;
import nonageshop.service.CartService;
import nonageshop.service.OrderService;

public class OrderInsertHandler implements Command {
	private CartService cartService = new CartService();
	private OrderService orderService = new OrderService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "orderList.do"; 

		if (request.getMethod().equalsIgnoreCase("post")) {
			HttpSession session = request.getSession();
			Member loginUser = (Member) session.getAttribute("loginUser");
			if (loginUser == null) {
				url = "loginform.do";
			} else {
				Orders orders = getOrders(loginUser);
				int maxOseq = orderService.addOrderAndDetail(orders);
				url = "orderList.do?no=" + maxOseq;
			}
			response.sendRedirect(url);
		}
		return null;
	}

	private Orders getOrders(Member member) {
		ArrayList<OrderDetail> details = new ArrayList<OrderDetail>();
		for(Cart c : cartService.listCartByMember(member)) {
			details.add(new OrderDetail(c));
		}
		Orders orders = new Orders();
		orders.setDetails(details);
		orders.setMember(member);
		return orders;
	}

}
