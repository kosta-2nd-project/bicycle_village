 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../pages/common/header.jsp"/>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자유게시판 목록</title>

<style type="text/css">
.btn {
  display: inline-block;
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  text-decoration: none;
  border-radius: 5px;
  margin-left: 10px;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

</head>
<body>

<div class="bg-light py-3" style="color: black;">
    <div class="container">
        <div class="row">
            <div class="col-md-12 mb-0" style="font-size: 15px; font-weight: bold;">자유게시판</div>
        </div>
    </div>
</div>

<div>
	<table class="container" align="center" width="100%";>

	<colgroup>
		<col width="17%"/>
		<col width="37%"/>
		<col width="10%"/>
		<col width="19%"/>
		<col width="7%"/>
		<col width="10%"/>
	</colgroup>
	
	<tr style="border-top: 1.5px solid black; border-bottom: 1px solid black;">
    <td style="text-align: center; padding: 10px;">
        <div style="display: table; height: 100%; width: 100%;">
            <div style="display: table-cell; vertical-align: middle;">
                <p align="center" style="color: black; font-weight: bold; margin: 0;">번호</p>
            </div>
        </div>
    </td>
    <td style="text-align: center; padding: 10px;">
        <div style="display: table; height: 100%; width: 100%;">
            <div style="display: table-cell; vertical-align: middle;">
                <p align="center" style="color: black; font-weight: bold; margin: 0;">제목</p>
            </div>
        </div>
    </td>
    <td style="text-align: center; padding: 10px;">
        <div style="display: table; height: 100%; width: 100%;">
            <div style="display: table-cell; vertical-align: middle;">
                <p align="center" style="color: black; font-weight: bold; margin: 0;">작성자</p>
            </div>
        </div>
    </td>
    <td style="text-align: center; padding: 10px;">
        <div style="display: table; height: 100%; width: 100%;">
            <div style="display: table-cell; vertical-align: middle;">
                <p align="center" style="color: black; font-weight: bold; margin: 0;">작성일</p>
            </div>
        </div>
    </td>
    <td style="text-align: center; padding: 10px;">
        <div style="display: table; height: 100%; width: 100%;">
            <div style="display: table-cell; vertical-align: middle;">
                <p align="center" style="color: black; font-weight: bold; margin: 0;">조회</p>
            </div>
        </div>
    </td>
    <td style="text-align: center; padding: 10px;">
        <div style="display: table; height: 100%; width: 100%;">
            <div style="display: table-cell; vertical-align: middle;">
                <p align="center" style="color: black; font-weight: bold; margin: 0;">댓글</p>
            </div>
        </div>
    </td>
</tr>
    

	<c:forEach items="${list}" var="boardDTO">
	    <tr style="background-color: #F8F8F8; border-top: 1px solid #BBBBBB; border-bottom: 1px solid #BBBBBB;">
	        <td style="text-align: center; width: 10%;">
	            <div style="display: table; height: 100%; width: 100%;">
	                <div style="display: table-cell; vertical-align: middle;">
	                    <p style="color: black; margin: 0; padding: 5px;">${boardDTO.boardSeq}</p>
	                </div>
	            </div>
	        </td>
	        <td style="width: 30%;">
	            <div style="display: table; height: 100%; width: 100%;">
	                <div style="display: table-cell; vertical-align: middle;">
	                    <p style="color: black; margin: 0; padding: 5px;">
	                    <a href="${path}/front?key=board&methodName=selectByTradeBoardSeq&boardSeq=${boardDTO.boardSeq}" style="color: black;">
	                    ${boardDTO.boardName}</a></p>
	                </div>
	            </div>
	        </td>
	        <td style="text-align: center; width: 10%;">
	            <div style="display: table; height: 100%; width: 100%;">
	                <div style="display: table-cell; vertical-align: middle;">
	                    <p style="color: black; margin: 0; padding: 5px;">${boardDTO.userDTO.userId}</p>
	                </div>
	            </div>
	        </td>
	        <td style="text-align: center; width: 20%;">
	            <div style="display: table; height: 100%; width: 100%;">
	                <div style="display: table-cell; vertical-align: middle;">
	                    <p style="color: black; margin: 0; padding: 5px;">${boardDTO.regDate}</p>
	                </div>
	            </div>
	        </td>
	        <td style="text-align: center; width: 10%;">
	            <div style="display: table; height: 100%; width: 100%;">
	                <div style="display: table-cell; vertical-align: middle;">
	                    <p style="color: black; margin: 0; padding: 5px;">${boardDTO.boardCount}</p>
	                </div>
	            </div>
	        </td>
	    </tr>
	</c:forEach>
</table>
	
</div>

<c:if test="${not empty loginUser}">
		<hr>
		<div align=right>
			<span style="font-size: 9pt;">
			<a href="${path}/board/tradeBoard/tradeBoardWrite.jsp" class="btn">글쓰기</a>
			</span>
		</div>
		<hr>
</c:if>



<%--   <jsp:useBean class="group2.bicycle_village.common.dto.PageCnt" id="p"/> 
   
    \${p.pageCnt} = ${p.pageCnt} / \${p.blockcount}   = ${p.blockcount}  <p> 

 
 
 <!--  블럭당  -->
<nav class="pagination-container">
		<div class="pagination">
		<c:set var="doneLoop" value="false"/>
		<c:set var="temp" value="${(pageNo-1) % p.blockcount}"/> <!-- (1-1)%2   , (2-1)%2    1 , (3-1)%2  0 -->
		<c:set var="startPage" value="${pageNo - temp}"/> <!--   1- 1 -->
		
	\${pageNo} = ${pageNo} , 	\${temp}=${temp}  ,   \${startPage}=${startPage}  , <br>
		 
		  <c:if test="${(startPage-p.blockcount) > 0}"> <!-- (-2) > 0  -->
		      <a class="pagination-newer" href="${path}/front?key=board&methodName=selectAll&pageNo=${startPage-1}">◀</a>
		  </c:if>
		
				<span class="pagination-inner"> 
				  <c:forEach var='i' begin='${startPage}' end='${(startPage-1)+p.blockcount}'> 
					  <c:if test="${(i-1)>=p.pageCnt}">
					       <c:set var="doneLoop" value="true"/>
					    </c:if> 
					  <c:if test="${not doneLoop}" >
					         <a class="${i==pageNo?'pagination-active':page}" href="${path}/front?key=board&methodName=selectAll&pageNo=${i}">${i}</a> 
					  </c:if>
				</c:forEach>
				</span> 
				
				 <c:if test="${(startPage+p.blockcount)<=p.pageCnt}">
				     <a class="pagination-older" href="${path}/front?key=board&methodName=selectAll&pageNo=${startPage+p.blockcount}">NEXT</a>
				 </c:if>
		
		</div>
	</nav>  --%>
	
<jsp:include page="../../pages/common/footer.jsp"/>
</body>
</html>


<tbody>