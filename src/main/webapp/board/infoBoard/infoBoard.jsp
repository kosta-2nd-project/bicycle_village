<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/pages/common/header.jsp"/>

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

.comment-update-form {
  display: none; /* 수정 폼은 초기에 숨김 */
}

</style>
<script type="text/javascript">
    
    function checkValid(){
        var f = window.document.commentForm;
        
        if ( f.commentContent.value == "" ) {
            alert( "댓글을 입력해 주세요." );
            f.commentContent.focus();
            return false;
        }
        
        return true;
    }

    // 댓글 수정폼 생성 함수
    function toggleEdit(commentSeq) {
        var editForm = document.getElementById("editForm_" + commentSeq);
        editForm.style.display = (editForm.style.display === "none") ? "block" : "none";
    }
</script>

</head>
<body>
	<div class="container">
		<div class="content">
			<p class="category">커뮤니티 게시판 > 정보게시판</p>
			<h1 class="title">${board.boardName}</h1>

			
			<h3 class="info" style="font-size: 20px">
				${board.regDate}&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;댓글 <c:out value="${commentList.size()}" /> 개
			</h3>

			<div class="nick_name" style="padding-bottom: 30px; font-size: 20px;">
				${board.userDTO.userId} &nbsp;&nbsp;
				<input type="button" id="follow" class="follow" value="팔로우">
			</div>

			<div class="content" align="center" style="height: auto; min-height: 100px; overflow: auto; font-size: 20px; color: black;">
			
				<%--다운로드 확인--%><div>    
				<a href="${path}/front?fname=cmd 명령어.txt">cmd 명령어.txt</a>    
				<a href="${path}/front?fname=file.txt">file.txt</a></div>
				${board.boardContent}
				
			</div>

			<div class="buttons">

				<c:choose>
					<c:when
						test="${user_seq == board.userSeq && not empty loginUser && UserStatus == 0}">
						<a href="${path}/front?key=board&methodName=selectByInfoBoardSeq&boardSeq=${board.boardSeq}" class="btn">목록</a>
						<a href="${path}/front?key=board&methodName=infoboardUpdateForm&boardSeq=${board.boardSeq}" class="btn">수정</a>
						<a href="${path}/front?key=board&methodName=deleteInfoBoard&boardSeq=${board.boardSeq}" class="btn">삭제</a>
					</c:when>
					<c:otherwise>
						<a href="${path}/front?key=board&methodName=selectByInfoBoardSeq&boardSeq=${board.boardSeq}" class="btn">목록</a>
					</c:otherwise>
				</c:choose>
			</div>
			
			<!-- 댓글 창 -->
			
			<hr>
			<h4 style="color: black; font-weight: bold;">댓글</h4>
			
			<c:forEach items="${commentList}" var="comment">
			    <div style="margin-bottom: 10px;">
			        <p style="margin: 0;">
			            <div style="display: flex; align-items: center;">
			                <b style="color: black; font-weight: bold; font-size: 18px; min-width: 130px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
			                    ${comment.userDTO.userId} </b>
			                <div id="comment_${comment.commentSeq}" style="color: black; font-size: 18px; min-width: 500px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis; margin-right: 10px;"
			                     onclick="makeEditable(this, '${comment.commentContent}')">
			                    <c:choose>
			                        <c:when test="${comment.isSeen==1}">
			                            ${comment.commentContent}
			                        </c:when>
			                        <c:otherwise>
			                            <b style="color: gray; font-size: 16px; font-weight: lighter;">삭제된 댓글입니다.</b>
			                        </c:otherwise>
			                    </c:choose>
			                </div>
			            </div>
			        <span style="color: #909090; font-size: 15px;"> 
			            
			            <c:choose>
			            	<c:when test="${comment.corDate!=null}">
			            		${comment.corDate}&nbsp;<b style="font-size: 12px;">(수정됨)&nbsp;&nbsp;&nbsp;</b>
			            	</c:when>
			            	<c:otherwise>
			            		${comment.regDate}&nbsp;&nbsp;&nbsp;
			            	</c:otherwise>
			            </c:choose>
			            
			            <c:if test="${comment.isSeen==1 && user_seq == comment.userSeq && not empty loginUser && UserStatus == 0}">
			                <a href="${path}/front?key=board&methodName=deleteComment&commentSeq=${comment.commentSeq}&category=INFORMATION&boardSeq=${board.boardSeq}"
			                   style="color: grey; font-size: 12px;">삭제</a> &nbsp;&nbsp;&nbsp; 
			                <label for="edit_${comment.commentSeq}" style="color: grey; font-size: 12px; cursor: pointer;" onclick="toggleEdit(${comment.commentSeq})">수정</label>
				        </c:if>
				    </span>
				   	<hr>
				   	
				   	<!-- 수정 폼 -->
                    <form id="editForm_${comment.commentSeq}" class="comment-update-form" name="commentUpdateForm" method="post"
                        action="${path}/front?key=board&methodName=updateComment&commentSeq=${comment.commentSeq}&category=INFORMATION&boardSeq=${board.boardSeq}">
                        <textarea name="commentUpdatedContent" cols="145" rows="3" style="color: grey">${comment.commentContent}</textarea>
                        <input type="submit" value="댓글 수정" id="commentUpdateSubmit" class="btn" style="float: right;"> <br><br>
                        <hr>
                    </form>
				   	
			    </div>
			</c:forEach>

			<hr>
			<c:if test="${not empty loginUser}">
				<p><b style="color: black;">댓글입력</b></p>
				<form name="commentForm" method="post"
					action="${path}/front?key=board&methodName=insertComment&boardSeq=${board.boardSeq}"
					onSubmit='return checkValid()' >
						<input type="hidden" name="category" value="INFORMATION">
                      	<input type="hidden" name="parent_comment" value="0">
                      	<input type="hidden" name="userSeq" value="${user_seq}">
                      	<input type="hidden" name="commentSeq" value="${comment.commentSeq}">
						<textarea name="commentContent" cols="145" rows="3"></textarea>
						<input type="submit" value="댓글 입력" id="commentSubmit" class="btn" style="float: right;"> <br>
					<br>
				</form>
			</c:if>
			
		</div>
	</div>

	<div class="container">
	</div>


	<jsp:include page="../../pages/common/footer.jsp"/>


</body>
</html>