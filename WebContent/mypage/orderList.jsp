<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp" %>  
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.jsp" %>       
	<article>
		<h2> Order List </h2>
		<form name="formm" method="post">
			<table id="cartList">
				<tr>
					<th>상품명</th> <th>수 량</th><th>가 격</th> <th>주문일</th> <th> 진행 상태 </th>    
				</tr>
			<c:forEach items="${orders.details}"  var="detail">
				<tr>      
					<td>
						<a href="productDetail.do?no=${detail.cart.product.no}">
              				<h3> ${detail.cart.product.name} </h3>              
          				</a>    
					</td>
					<td> ${detail.cart.quantity} </td>
					<td> <fmt:formatNumber value="${detail.cart.product.salePrice * detail.cart.quantity}" type="currency"/> </td>      
					<td> <fmt:formatDate value="${detail.orderDate}" type="date"/></td>
					<td> 처리 진행 중 </td>
				</tr>
			</c:forEach>
				<tr>
					<th colspan="2"> 총 액 </th>
					<th colspan="2"> <fmt:formatNumber value="${totalPrice}" type="currency"/><br>     </th> 
					<th> 주문 처리가 완료되었습니다. </th>                
				</tr> 
			</table>   
          
			<div class="clear"></div>
			<div id="buttons" style="float: right">
				<input type="button" value="쇼핑 계속하기"  class="cancel"  onclick="location.href='index.do'">     
			</div>
		</form>  
	</article>
<%@ include file="../footer.jsp" %>