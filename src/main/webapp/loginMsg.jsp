<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
	if(confirm("회원님은 현재 탈퇴 상태입니다. 다시 가입하시겠습니까?")){
		//console.log("${userId}");
		location.href="user/signUp2.jsp";
	}else{
		location.href="index.jsp";
	}
</script>

</body>
</html>