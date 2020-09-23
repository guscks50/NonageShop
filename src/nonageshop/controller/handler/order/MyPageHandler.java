package nonageshop.controller.handler.order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.dto.Orders;
import nonageshop.service.OrderService;

public class MyPageHandler implements Command {
	private OrderService orderService = new OrderService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "mypage/mypage.jsp";
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) {
			url = "loginform.do";
		} else {
			ArrayList<Integer> orderNoList = orderService.selectSeqOrderIng(loginUser);
			ArrayList<Orders> orders = new ArrayList<Orders>();
			for (int orderNo : orderNoList) {
				orders.add(orderService.orderListByMember(loginUser.getId(), orderNo, "1"));
				
			}
			request.setAttribute("title", "진행 중인 주문 내역");
			request.setAttribute("ordersList", orders);
		}

		return url;
	}

}
