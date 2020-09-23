<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp" %>  
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.jsp" %>   
  <article>
    <h2> Item</h2>     
    <c:forEach items="${productKindList }"  var="product">
      <div id="item">
        <a href="productDetail.do?no=${product.no}"> 
          <img src="product_images/${product.image}" />
          <h3>${product.name} </h3>        
          <p>${product.salePrice} </p>
        </a>  
      </div>
    </c:forEach>    
    <div class="clear"></div>
  </article>
<%@ include file="../footer.jsp" %>    