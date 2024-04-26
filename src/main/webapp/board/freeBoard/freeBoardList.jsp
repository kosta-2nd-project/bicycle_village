 <%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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

<script type="text/javascript">

	$(function(){
		//전체검색
		   function selectAll(){
			   $.ajax({
				url :"ajax" , //서버요청주소
				type:"post", //요청방식(method방식 : get | post | put | delete )
				dataType:"json"  , //서버가 보내온 데이터(응답)타입(text | html | xml | json )
				data: {key:"customer" , methodName : "selectAll"}, //서버에게 보낼 데이터정보(parameter정보)
				success :function(result){
					console.log(result);
					
					let str="";
					$.each(result, function(index, item){
					    str+="<tr>";
					    str+=`<td>${(item.boardSeq)}</td>`;
					    str+=`<td>${item.boardName}</td>`;
					    str+=`<td>${item.userSeq}</td>`;
					    str+=`<td>${item.regDate}</td>`;
					    str+=`<td>${item.boardCount}</td>`;
					    str+=`<td>${item.comment}</td>`;
					    str+="</tr>";
				  });
					
					$("#listTable tr:gt(0)").remove();
					$("#listTable tr:eq(0)").after(str);//형제노드로 뒤에 추가
					
				} , //성공했을때 실행할 함수 
				error : function(err){  
					alert(err+"에러 발생했어요.");
				}  //실팽했을때 실행할 함수 
			});//ajax끝
		   }//selectAll 함수끝
		   /////////////////////////////////////////////////////////////
		   
		
	});
	
</script>

</head>
<body>
	<table id="listTable" cellspacing="0">
	<tr bgcolor="skyblue">
					<th>번호</th>
					<th class="title">제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회</th>
					<th>댓글</th>
				</tr>
			</th>
		</table>
</body>
</html>


<tbody>