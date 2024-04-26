<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자유게시판</title>

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
		

/* 		if (f.goods_price.value == "") {
			alert("가격을 입력해 주세요.");
			f.goods_price.focus();
			return false;
		} */
		
/* 		if (f.image_name == "") {
			alert("이미지를 1개 이상 입력해 주세요.");
			f.image_name.focus();
			return false;
		} */
		return true;

	}
</script>

</head>
<body>

	<form name="writeForm" method="post"
		action="${path}/front?key=board&methodName=insert"
		onSubmit='return checkValid()' enctype="multipart/form-data">

		<table align="center" cellpadding="5" cellspacing="2" width="600" border="1">
		
			<tr>
				<td width="1220" height="20" colspan="2" bgcolor="#00cc00">
					<p align="center">
						<font color="white" size="3"><b> 상품 등록 </b></font>
					</p>
				</td>
			</tr>

			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">카테고리</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <!-- <select name="category">
								<option value="">카테고리</option>
								<option value="free">자유게시판</option>
								<option value="trade">중고거래게시판</option>
						</select> -->
						<b><span style="font-size: 9pt;">자유게시판</span></b>
					</span></b></td>
			</tr>

			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">제목</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type="text" size="30"
							name="board_name" id="board_name"></span></b></td>
			</tr>
			
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">이미지 첨부</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <input type="text" size="30"
							name="img" id="img"> <input type="file" name="file"
							maxlength="60" size="40">
					</span></b></td>
			</tr>

			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">설명</span></b>
					</p>
				</td>
				<td width="450" height="20"><b><span
						style="font-size: 9pt;"> <textarea name="board_content"
								cols="50" rows="10"></textarea></span></b></td>
			</tr>

		</table>
	<div align=center><span style="font-size:9pt;"><input type=submit value="게시글 등록"
							id="submit"></div>
							
	<input type="hidden" name="category" value="FREE">
	<input type="hidden" name="board_count" value="0">
	<input type="hidden" name="goods_price" value="0">
	<input type="hidden" name="product_seq" value="0">
	<input type="hidden" name="is_seen" value="AVAILABLE">
	<input type="hidden" name="board_addr" value="서울어딘가">
	<input type="hidden" name="user_seq" value="${user_seq}">
	</form><br>
	
	
	
	<br>
</body>
</html>