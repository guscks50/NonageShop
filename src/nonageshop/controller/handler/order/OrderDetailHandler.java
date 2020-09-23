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

public class OrderDetailHandler implements Command {
    private OrderService orderService = new OrderService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "mypage/orderDetail.jsp";

        HttpSession session = request.getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            url = "loginform.do";
        } else {
            int orderNo = Integer.parseInt(request.getParameter("no"));
            Orders orders = orderService.orderListByMember(loginUser.getId(), orderNo, "%");

            int totalPrice = 0;
            for (OrderDetail od : orders.getDetails()) {
                totalPrice += od.getCart().getProduct().getSalePrice() * od.getCart().getQuantity();
            }
            request.setAttribute("orderDetail", orders.getDetails().get(0));
            request.setAttribute("orders", orders);
            request.setAttribute("totalPrice", totalPrice);
        }
        return url;
    }

}
