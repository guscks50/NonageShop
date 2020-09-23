package nonageshop.dao;

import java.util.ArrayList;

import nonageshop.dto.Member;
import nonageshop.dto.Orders;

public interface OrderDao {
    int selectMaxOrdersNo();

    /*    
    OrderService로 이동
    int insertOrder(Member member);//orders테이블과 detail테이블 추가 트랜잭션적용
    void insertOrderDetail(Cart cart, int ordersNo);
      */      
    
    Orders listOrderByMember(String memberId, int orderNo, String result);
    
    ArrayList<Integer> selectSeqOrderIng(Member member);
}
