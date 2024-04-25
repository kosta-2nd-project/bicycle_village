<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="common/header.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<%-- <c:choose>
	<c:when test="${empty loginUser || UserStatus !=0}">
		console.log("${pageContext.request.contextPath}");
		<a href="${pageContext.request.contextPath}/user/signUp.jsp">ㅇㅇ</a>
	</c:when>
	
	<c:otherwise>
		<div class="alert alert-dismissible alert-warning">
			<h4>${loginName}님 반갑습니다!</h4>
		</div>
	</c:otherwise>
</c:choose> --%>



</body>
</html>

 <jsp:include page="common/footer.jsp"/>