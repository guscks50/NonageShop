package nonageshop.controller.handler.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.dto.OrderDetail;
import nonageshop.dto.Orders;
import nonageshop.service.OrderService;

public class OrderListHandler implements Command {
	private OrderService service = new OrderService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "mypage/orderList.jsp";
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");

		if (loginUser == null) {
			url = "loginform.do";
		} else {
			int no = Integer.parseInt(request.getParameter("no"));
			Orders orders = service.orderListByMember(loginUser.getId(), no, "1");
			System.out.println("orders > " + orders);
			int totalPrice = 0;
			for (OrderDetail detail : orders.getDetails()) {
				totalPrice += detail.getCart().getProduct().getSalePrice() * detail.getCart().getQuantity();
			}
			request.setAttribute("orders", orders);
			request.setAttribute("totalPrice", totalPrice);
		}
		return url;
	}

}
