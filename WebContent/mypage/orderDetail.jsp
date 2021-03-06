<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp" %>  
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.jsp" %>       
  <article>
    <h2> My Page(주문 상세 정보) </h2>
    <form name="formm" method="post">
      <h3> 주문자 정보 </h3>     
      <table id="cartList">      
      <tr>
        <th>주문일자</th> <th>주문번호</th> <th>주문자</th><th>주문 총액</th>     
     </tr>     
     <tr>  
       <td> <fmt:formatDate value="${orderDetail.orderDate}" type="date"/></td>
       <td> ${orderDetail.no} </td>    
       <td> ${orders.member.name} </td>
       <td> <fmt:formatNumber type="currency" value="${totalPrice}" /> </td>
     </tr>    
     </table>         
     <h3> 주문 상품 정보 </h3> 
     <table id="cartList">
     <tr>
       <th>상품명</th> <th>상품별주문번호</th> <th>수량</th> <th>가격</th> <th>처리 상태</th>    
     </tr>
     <c:forEach items="${orders.details}"  var="orderDetail">
     <tr>
       <td> ${orderDetail.cart.product.name} </td>
       <td> ${orderDetail.no} </td>          
       <td> ${orderDetail.cart.quantity} </td>
       <td> <fmt:formatNumber type="currency" value="${orderDetail.cart.product.salePrice * orderDetail.cart.quantity}" /> </td>
       <td>
         <c:choose>
         	<c:when test='${orderDetail.result=="1"}'> 진행중 </c:when>
         	<c:otherwise> <span style="color:red"> 처리완료 </span></c:otherwise>
         </c:choose>
       </td>
     </tr>
     </c:forEach>    
     </table>   
          
     <div class="clear"></div>
     <div id="buttons" style="float: right">
       <input type="button"    value="쇼핑 계속하기"  class="cancel"  onclick="location.href='index.do'">
     </div>
    </form>  
  </article>
<%@ include file="../footer.jsp" %>