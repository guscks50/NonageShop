<%@ page language="java" contentType="text/html; charset=UTF-8"%>


<%@ include file="../header.jsp" %>  
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.jsp" %>   
  <article>
    <h1> Item </h1>
    <div id="itemdetail" >
      <form  method="post" id="cartform">    
        <fieldset>
          <legend> Item detail Info</legend>  
          <a href="productDetail.do?no=${product.no}">         
            <span style="float: left;">
              <img  src="product_images/${product.image}"  />
            </span>              
            <h2> ${product.name} </h2>  
          </a>    
          <label> 가 격 :  </label>  
          <p> ${product.salePrice} 원</p>  
          <label> 수 량 : </label>
          <input  type="number"      id="quantity" name="quantity"  size="2"      value="1"><br>
          <input  type="hidden"    name="no"       value="${product.no}"><br>
        </fieldset>
        <div class="clear"></div>
        <div id="buttons">
          <input type="button" value="장바구니에 담기"   id="gocart" class="submit" > 
          <input type="button" value="즉시 구매"       id="goorder" class="submit" > 
          <input type="reset"  value="취소"           class="cancel">
        </div>
      </form>  
    </div>
  </article>
<%@ include file="../footer.jsp" %>    

