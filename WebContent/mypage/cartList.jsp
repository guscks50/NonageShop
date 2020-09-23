<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp" %>  
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.jsp" %>       
  <article>
    <h2> Cart List </h2>
    <form name="form" method="post" id="cartform">
    <c:choose>
    <c:when test= "${cartList.size() == 0}">
      <h3 style="color: red;text-align: center;"> 장바구니가 비었습니다. </h3> 
    </c:when>
    <c:otherwise>
      <table id="cartList">
        <tr>
          <th>상품명</th><th>수 량</th><th>가 격</th><th>주문일</th><th>삭 제</th>    
        </tr>
        
        <c:forEach items="${cartList}"  var="cart">
        <tr>      
          <td>
            <a href="productDetail.do?no=${cart.product.no}">
              <h3> ${cart.product.name} </h3>              
            </a>    
          </td>
          <td> ${cart.quantity} </td>
          <td> 
            <fmt:formatNumber value="${cart.product.salePrice * cart.quantity}" pattern="#,###.##원" /> 
          </td>      
          <td> <fmt:formatDate value="${cart.regDate}" type="date"/></td>      
          <td> <input type="checkbox" name="no" value= "${cart.no}"> 
          </td>
        </tr>
        </c:forEach>
         
        <tr>        
          <th colspan="2"> 총 액 </th>
          <th colspan="2"> 
            <fmt:formatNumber value="${totalPrice}" pattern="#,###.##원" /><br>
          </th> 
          <th><a href="#" id="cart_delete"><h3>삭제하기</h3></a></th>                       
        </tr> 
      </table> 
    </c:otherwise>  
    </c:choose>  
     
    <div class="clear"></div>
     
    <div id="buttons" style="float: right">
      <input type="button" id="continue" value="쇼핑 계속하기" class="cancel" >    
      <c:if test= "${cartList.size() != 0}">
      <input type="button" value="주문하기"  id="go_order_insert" class="submit" >
      </c:if>
     </div>
    </form>
  </article>
<%@ include file="../footer.jsp" %>