 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
 .a{border:solid red 5px}
 span{width:150px; color:red}
 input{border:solid gray 1px}
 table{width:100%}
 th,td{border:1px gray solid; text-align:center;padding:3px}
 h2{text-align:center}
 
 a{text-decoration: none;}
 a:hover{color: red}
 
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

</head>
<body>

		<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
<caption><h2 align="center">자유게시판</h2></caption>
	<colgroup>
		<col width="15%"/>
		<col width="37%"/>
		<col width="16%"/>
		<col width="16%"/>
		<col width="7%"/>
		<col width="7%"/>
	</colgroup>
	<tr>
        <td bgcolor="#00cc00">
            <p align="center">
            <font color="white"><b><span style="font-size:9pt;">번호</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">제목</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">작성자</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">작성일</span></b></font></p>
        </td>
        
        
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">조회</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">댓글</span></b></font></p>
        </td>
    </tr>
    

	<c:forEach items="${list}" var="boardDTO">
		    <tr onmouseover="this.style.background='#eaeaea'"
		        onmouseout="this.style.background='white'">
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${boardDTO.boardSeq}</span></p>
		        </td>
		        <td bgcolor="">
					<p><span style="font-size:9pt;">
					<a href="${path}/front?key=board&methodName=selectByBoardSeq&boardSeq=${boardDTO.boardSeq}">
					  ${boardDTO.boardName}
					</a>
					</span></p>
		        </td>
		        
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${boardDTO.userSeq}</span></p>
		        </td>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${boardDTO.regDate}</span></p>
		        </td>
		        
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${boardDTO.boardCount}</span></p>
		        </td>
		         
		        <%-- <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            <a action="${path}/front?key=comment&methodName=countCommentsByBoardSeq&boardSeq=${boardDTO.boardSeq}">
					</a>
		            댓글의 개수가 올 곳</span></p>
		        </td>

				<td bgcolor="">
					<p align="center"><span style="font-size: 9pt;"> 
					<c:out value="${commentCounts[boardDTO.boardSeq]}"></c:out>
						</span>
					</p>
				</td> --%>

			</tr>
    </c:forEach>
</table>
<hr>
<div align=right>
<span style="font-size:9pt;">&lt;<a href="${path}/board/freeBoard/freeBoardWrite.jsp">글쓰기</a>&gt;</span>
</div>
<hr color="red">

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
		
		
		
		
		
		
		
		
		
		
		
</body>
</html>


<tbody>