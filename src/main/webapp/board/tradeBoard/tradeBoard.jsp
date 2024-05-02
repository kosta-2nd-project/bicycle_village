<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../pages/common/header.jsp"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>게시판</title>

	<style type="text/css">
		body {
			font-family: Arial, sans-serif;
			margin: 0;
			padding: 0;
		}

		.container {
			width: 1200px;
			margin: 0 auto;
			padding: 20px;
		}

		.content {
			background-color: #fff;
			padding: 20px;
			border-radius: 10px;
			box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
			margin-bottom: 20px;
		}

		.title {
			font-size: 36px;
			font-weight: bold;
			color: #000;
			margin-bottom: 10px;
		}

		.category {
			font-size: 18px;
			color: #909090;
			margin-bottom: 20px;
		}

		.nick_name {
			font-size: 18px;
			color: #909090;
		}

		.info {
			display: flex;
			justify-content: space-between;
			margin-bottom: 20px;
		}

		.address {
			display: flex;
			justify-content: space-between;
			margin-bottom: 20px;
		}

		.date {
			font-size: 18px;
			color: #909090;
		}

		.comment {
			font-size: 18px;
			color: #909090;
		}

		.content-text {
			font-size: 16px;
			color: #000;
			margin-bottom: 20px;
		}

		.buttons {
			text-align: right;
		}

		.btn {
			display: inline-block;
			padding: 10px 20px;
			background-color: #007bff;
			color: #fff;
			text-decoration: none;
			border-radius: 5px;
			margin-left: 10px;
		}

		.follow {
			border-radius: 1em;
			font-size: 14px;
			color: white;
			background-color: #717171;
			border: none;
			padding-left: 20px;
			padding-right: 20px;
			padding-top: 4px;
		}

		.chatting {
			border-radius: 0.5em;
			font-size: 16px;
			color: white;
			background-color: #7971EA;
			border: none;
			padding-left: 20px;
			padding-right: 20px;
			padding-top: 4px;
		}

		#dip {
			position: relative;
			width: 30px;
			height:  30px;
			border: none; /* Remove the border */
			background-color: transparent; /* Set background color to transparent */
			margin-right: 20px;
		}

		#dip:before, #dip:after {
			position: absolute;
			content: "";
			left: 15px;
			top: 0;
			width: 15px;
			height: 25px;
			background: red;
			border-radius: 50px 50px 0 0;
			transform: rotate(-45deg);
			transform-origin: 0 100%;
		}

		#dip:after {
			left: 0;
			transform: rotate(45deg);
			transform-origin: 100% 100%;
		}

		.main_left_img {
			width: 300px;
			height: 300px;
			background-color: red;
			float: left;
			margin-right: 70px;
			background-image: url("${path}/file-servlet?fname=${board.boardSeq}/${imageName}");
			background-size: cover
		}

		.comment-update-form {
			display: none; /* 수정 폼은 초기에 숨김 */
		}
	</style>

	<script src="${path}/js/jquery-3.3.1.min.js"></script>
	<script>
		console.log("${user_seq}");
		console.log("${userId}");
		console.log("${nickname}");

		$(function(){
			//버튼 누르면 찜 추가
			$(document).on("click","#dip",function(){
				$.ajax({
					url:"${path}/rest",
					type:"post",
					dataType:"json",
					data:{key:"bookmark",methodName:"addBookmark",boardSeq:"${board.boardSeq}"},
					success:function(result){
						console.log("result:"+result);
						if(result===1){
							alert("이미 찜하기한 게시글입니다.");
						}else{
							alert("찜 목록에 추가됨");
						}
					},
					error:function(err){
						alert(err+"에러 발생");
					}
				});
			});//찜 추가 End

			//팔로우 추가
			$(document).on("click","#follow",function(){
				$.ajax({
					url:"${path}/rest",
					type:"post",
					dataType:"json",
					data:{key:"follow",methodName:"addFollow",userId:"${board.userDTO.userId}"},
					success:function(result){
						console.log("result:"+result);
						if(result===1){
							alert("이미 팔로우 중입니다.");
						}else{
							alert("팔로우 목록에 추가됨")
						}
					},
					error:function(err){
						alert(err+"에러 발생");
					}
				})
			});
		});//function End

		// 댓글 수정폼 생성 함수
		function toggleEdit(commentSeq) {
			let editForm = document.getElementById("editForm_" + commentSeq);
			editForm.style.display = (editForm.style.display === "none") ? "block" : "none";
		}

		function checkValid(){
			var f = window.document.commentForm;

			if ( f.commentContent.value == "" ) {
				alert( "댓글을 입력해 주세요." );
				f.commentContent.focus();
				return false;
			}

			return true;
		}

	</script>
</head>
<body>
<div class="container">
	<div class="main_left_img">
		이미지
	</div>
	<div class="main">
		<p class="category">거래 게시판 > </p>
		<h1 class="title">${board.boardName}</h1>


		<h3 class="info" style="font-size: 20px">
			${board.regDate}&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;댓글
		</h3>

		<h3 class="address" style="font-size: 17px">
			${board.boardAddr}
		</h3>

		<div class="nick_name" style="padding-bottom: 30px; font-size: 20px;">
			${board.userDTO.userId} &nbsp;&nbsp;
			<input type="button" id="follow" class="follow" value="팔로우">
		</div>


		<div class="chattingAndDip"
			 style="padding-bottom: 30px; font-size: 20px;">
			<script>
				function openChat(event) {
					let boardSeq= '${board.boardSeq}'
					let userId = '${loginId}'// 사용자명을 이곳에 설정
					let sellerId =  '${board.userDTO.userId}'
					console.log(userId+"userId")
					axios.post('http://localhost:3000/?sellerId', {
						userId: userId,
						boardSeq : boardSeq,
						sellerId : sellerIdq
					})
							.then(function (response) {
								console.log(response);
							})
							.catch(function (error) {
								console.log(error);
							});

					window.open("http://localhost:3000?sellerId="+sellerId+"&userId="+userId+"&boardSeq="+boardSeq	, '_blank', 'width=700, height=600, top=50, left=50, scrollbars=yes');
				}  //판매자의 아이디도 같이 넘겨서 ,
			</script>


			<script>
				function openChat2(event) {
					let boardSeq= '${board.boardSeq}'
					let userId = '${loginId}'// 사용자명을 이곳에 설정
					let sellerId =  '${board.userDTO.userId}'
					console.log(userId+"userId")
					axios.post('http://localhost:3000/?sellerId', {
						userId: userId,
						boardSeq : boardSeq,
						sellerId : sellerId

					})
							.then(function (response) {
								console.log(response);
							})
							.catch(function (error) {
								console.log(error);
							});

					window.open("http://localhost:3000/?sellerId=test123&userId=ww"	, '_blank', 'width=700, height=600, top=50, left=50, scrollbars=yes');
				}  //판매자의 아이디도 같이 넘겨서 ,
			</script>



			<button  id="dip" class="dip"></button>
			<input type="button" id="chatting2" class="chatting"  onclick="openChat2()" value="이사람하고 채팅하기">
			<input type="button" id="chatting" class="chatting"  onclick="openChat()" value="채팅하기">
		</div>

	</div>



	<div class="container">


		<div class="content"
			 style="height: auto; min-height: 100px; overflow: auto;">
			${board.boardContent}</div>

		<div class="buttons">

			<c:choose>
				<c:when
						test="${user_seq == board.userSeq && not empty loginUser && UserStatus == 0}">
					<a href="${path}/front?key=board&methodName=selectAllTradeBoard" class="btn">목록</a>
					<a href="${path}/front?key=board&methodName=tradeboardUpdateForm&boardSeq=${board.boardSeq}"
					   class="btn">수정</a>
					<a href="${path}/front?key=board&methodName=deleteTradeBoard&boardSeq=${board.boardSeq}"
					   class="btn">삭제</a>
				</c:when>
				<c:otherwise>
					<a href="${path}/front?key=board&methodName=selectAllTradeBoard" class="btn">목록</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

<jsp:include page="../../pages/common/footer.jsp"/>

</body>
</html>
