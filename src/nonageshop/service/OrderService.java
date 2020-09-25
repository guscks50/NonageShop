package nonageshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageshop.dao.impl.CartDaoImpl;
import nonageshop.dao.impl.OrderDaoImpl;
import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Member;
import nonageshop.dto.OrderDetail;
import nonageshop.dto.Orders;

public class OrderService {
    private OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
    private CartDaoImpl cartDao = CartDaoImpl.getInstance();
    
    public OrderService() {
    	orderDao.setCon(JdbcUtil.getConnection());
    	cartDao.setCon(JdbcUtil.getConnection());
	}

    public ArrayList<Integer> selectSeqOrderIng(Member member){
    	return orderDao.selectSeqOrderIng(member);
    }
    
    public Orders orderListByMember(String memberId, int orderNo, String result) {
    	return orderDao.listOrderByMember(memberId, orderNo, result);
    }
    
    public int maxOrderNo() {
    	return orderDao.selectMaxOrdersNo();
    }
    
    
    public int addOrderAndDetail(Orders orders) {
        String ordersSql = "INSERT INTO ORDERS(ID) VALUES(?)";
        String detailSql = "INSERT INTO ORDER_DETAIL (ONO, PNO, QUANTITY, result_yn) VALUES(?, ?, ?,'1')";
        Connection con = null;
        PreparedStatement orderPstmt = null;
        PreparedStatement detailPstmt = null;
        int ordersMaxNo = 0;
        
        try {
            con = JdbcUtil.getConnection();
            con.setAutoCommit(false);
            
            orderPstmt = con.prepareStatement(ordersSql);
            orderPstmt.setString(1, orders.getMember().getId());
            orderPstmt.executeUpdate();
            
            detailPstmt = con.prepareStatement(detailSql);
            ordersMaxNo = maxOrderNo() + 1;
            System.out.println("orderMaxNo" + ordersMaxNo);
            for(OrderDetail od : orders.getDetails()) {
                detailPstmt.setInt(1, ordersMaxNo);
                detailPstmt.setInt(2, od.getCart().getProduct().getNo());
                detailPstmt.setInt(3, od.getCart().getQuantity());
                detailPstmt.executeUpdate();
                
                cartDao.updateCartResult(od.getCart());
            }
            
            con.commit();
        } catch (SQLException e) {
            rollbackUtil(con, e);
        } finally {
            closeUtil(con, orderPstmt, detailPstmt);
        }
        return ordersMaxNo;
    }

    private void rollbackUtil(Connection con, SQLException e) {
        try {
            System.out.println("roll back");
            con.rollback();
            throw new RuntimeException(e);
        } catch (SQLException ex) {
        }
    }

    private void closeUtil(Connection con, PreparedStatement dPstmt, PreparedStatement tPstmt) {
        try {
            if (dPstmt != null) {
                dPstmt.close();
            }
            if (tPstmt != null) {
                tPstmt.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException ex) {
        }
    }
}
