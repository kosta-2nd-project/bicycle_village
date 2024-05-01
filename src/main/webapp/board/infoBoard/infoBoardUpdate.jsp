<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../pages/common/header.jsp"/>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>정보게시판</title>

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
    <form name="writeForm" method="post" action="${path}/front?key=board&methodName=updateInfoBoard&boardSeq=${board.boardSeq}" onSubmit='return checkValid()' enctype="multipart/form-data">
        <p><b>거래게시판 > 정보게시판</b></p>

        <p>제목 &nbsp; &nbsp; &nbsp;
        <input type="text" size="60" name="board_name" id="board_name" value="${board.boardName}"></p>
        
        <p><input type="file" name="files" multiple></p>

        <hr>
        <p><b>상품 설명</b></p>
        <p><textarea name="board_content" cols="95%" rows="10">${board.boardContent}</textarea></p>

        <p align="center">
            <input type="submit" value="게시글 수정" id="submit">
        </p>
        
        <input type="hidden" name="category" value="INFO">
        <input type="hidden" name="board_count" value="0">
        <input type="hidden" name="goods_price" value="0">
        <input type="hidden" name="product_seq" value="0">
        <input type="hidden" name="is_seen" value="AVAILABLE">
        <input type="hidden" name="board_addr" value="">
        <input type="hidden" name="user_seq" value="${user_seq}">
    </form>
</div>

<jsp:include page="../../pages/common/footer.jsp"/>
</body>
</html>