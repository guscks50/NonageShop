<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nonage Admin</title>
<link rel="stylesheet" href="admin/css/admin.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
<script type="text/javascript">
$(function(){
	$('#login').on("click", function(){
        if($('#login').val().length==0){
            alert("아이디를 입력하세요.");
            return false;
        }else if($('#workerPwd').val().length==0){
           	alert("비밀번호를 입력하세요.");
            return false;
        }else{
            $('#frm').submit();
        }
    });
});
</script>
</head>

<body>
  <div id="wrap">
    <header>      
      <div id="logo">
        <a href="admin/main.jsp"> 
        <img src="admin/images/bar_01.gif" style="float:left">
        <img src="admin/images/text.gif">
        </a>
      </div>      
    </header>
    <div class="clear"></div>
    <article>
      <div id="loginform">
      <form id="frm" method="post" action="adminLogin.do">
      <table>
        <tr>
          <td> 아 이 디 </td>
          <td> <input type="text" id="workerId" name="workerId" size="10" value="admin"></td>
        </tr>
        <tr>
          <td> 비밀번호 </td>
          <td> 
            <input type="password" id="workerPwd" name="workerPwd" size="10" value="admin">
          </td>
        </tr>
        <tr align="center" >
          <td  colspan="2">          
            <input class="btn" type="submit" id="login" value="업무 로그인"><br><br>
            <h4 style="color:red">${message}</h4>
          </td>
        </tr>
      </table>
      </form>
      </div>
    </article>
  </div>
</body>
</html>