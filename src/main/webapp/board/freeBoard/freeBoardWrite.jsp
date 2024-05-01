<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../pages/common/header.jsp"/>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자유게시판</title>

<style type="text/css">
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
}

.board {
	width: 50%; /* You can adjust the width as needed */
	margin: 50px auto; /* Center horizontally */
	padding: 20px;
	border: 1px solid #ccc; /* Add border */
	border-radius: 10px; /* Add border radius */
	color: black; /* Set text color to black */
}

#imageContainer {
	margin-bottom: 20px;
	border: none;
}

#imageContainer div {
	border: 1px solid #ccc; /* Add border */
	padding: 10px;
	margin-bottom: 10px;
}
</style>

<script type="text/javascript" >
	function checkValid(){
		var f = window.document.writeForm;
		
		if ( f.board_name.value == "" ) {
			alert( "제목을 입력해 주세요." );
			f.board_name.focus();
			return false;
		}
	  
		if ( f.board_content.value == "" ) {
	        alert( "상품 설명을 입력해 주세요." );
	        f.board_content.focus();
	        return false;
	    }
		
		return true;
	}
</script>


</head>
<body>

<div class="board">
    <form name="writeForm" method="post" action="${path}/front?key=board&methodName=insertFreeBoard"
     onSubmit='return checkValid()' enctype="multipart/form-data">
       
			<p><b>카테고리 > 자유게시판</b></p>

			<p>제목 &nbsp; &nbsp; &nbsp;
			<input type="text" size="60" name="board_name" id="board_name"></p>
			
			<%--게시글 작성--%>   
			<input type="file" name="files" multiple>
			
			<hr>
			<p><b>상품 설명</b></p>
			<p><textarea name="board_content" cols="96%" rows="10"></textarea></p>

			<p align="center">
				<input type="submit" value="게시글 등록" id="submit">
			</p>
								
			<input type="hidden" name="category" value="FREE">
			<input type="hidden" name="board_count" value="0">
			<input type="hidden" name="goods_price" value="0">
			<input type="hidden" name="product_seq" value="0">
			<input type="hidden" name="is_seen" value="AVAILABLE">
			<input type="hidden" name="board_addr" value="대한민국">
			<input type="hidden" name="user_seq" value="${user_seq}">
		</form>
</div>

<jsp:include page="../../pages/common/footer.jsp"/>
</body>
</html>