<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping</title>

<script 
src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>

</head>
<body>

<div class="navbar navbar-default">
 
  <div class="navbar-collapse collapse navbar-responsive-collapse">
    
    <ul class="nav navbar-nav">
    
      <li><a href="${pageContext.request.contextPath}">Home</a>
      <li><a href="${pageContext.request.contextPath}/index.jsp">Home2</a>
      <li><a href="${pageContext.request.contextPath}/user/login.jsp">로그인</a></li>
      <li><a href="${pageContext.request.contextPath}/user/signUp.jsp">회원가입</a>
      <li><a href="${pageContext.request.contextPath}/user/myPage.jsp">마이페이지</a>
      
     </ul>
     
     
     <!-- 로그인 되면 sessionScope에 loginUser 저장되어있다! -->
     <c:if test="${not empty loginUser && UserStatus ==0}">
	     <ul class="nav navbar-nav navbar-right">
	     	<li><a>${loginName}님 로그인 중</a>
	      	<li><a href="${pageContext.request.contextPath}/front?key=user&methodName=logout" class="btn btn-danger">Logout</a></li>
	     </ul>
     </c:if>
  </div>
</div>



