<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="${path}/pages/common/header.jsp"/>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Title</title>
<script src="${path}/js/jquery-3.3.1.min.js"></script>
<script>
$(function(){
	function selectAll(){
	$.ajax({
		url:"${path}/rest",
		type:"post",
		dataType:"json",
		data:{key:"bookmark", methodName:"selectAll"},
		success:function(result){
			let str="";
			$.each(result,function(index,item){
				console.log(item);
				str+=`<tr>`;
				str+=`<td>이미지</td>`;
				str+=`<td><a>\${item.boardName}</a></td>`;
				str+=`<td>\${item.nickname}</td>`;
				str+=`<td>\${item.regDate}</td>`;
				str+=`<td>\${item.goodsPrice}</td>`;
				str+=`<td>\${item.boardAddr}</td>`;
				str+=`<td><input type ='button' class="btn btn-primary btn-sm" value='X' name=\${item.boardSeq}></td>`;
				str+=`</tr>`;
			});
			$("table tr:gt(0)").remove();
			$("table tr:eq(0)").after(str);
		},
		error:function(err){
			//alert("error: "+err);
		}
	});
	}//select All End
	
	//삭제하기
	$(document).on("click","[value=X]",function(){
		$.ajax({
			url:"${path}/rest",
			type:"post",
			dataType:"text",
			data:{key:"bookmark",methodName:"delBookmark",seq:$(this).attr("name")},
			success:function(result){
				if(result==0){
					alert("삭제되지 않았습니다.");
				}else{
					selectAll();
				}
			},
			error: function(err){
				alert(err+"에러 발생");
			}
		});
	});
	
	selectAll(); //전체 조회
});

</script>
</head>
  
  <body>
  
  <div class="site-wrap">

    <div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0"><a href="${path}/index.jsp">Home</a> <span class="mx-2 mb-0">/</span> <a href="${path}/pages/user/myPage.jsp">마이페이지</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">찜 목록</strong> </div>
        </div>
      </div>
    </div>

    <div class="site-section">
      <div class="container">
      
      <c:choose>
      <c:when test="${not empty loginUser && UserStatus ==0}">
        <div class="row mb-5">
          <form class="col-md-12" method="post">
            <div class="site-blocks-table">
              <table class="table table-bordered">
                
                  <tr>
                    <th class="product-thumbnail">대표 사진</th>
                    <th class="product-name">게시글 제목</th>
                    <th class="product-price">작성자</th>
                    <th class="product-quantity">작성일</th>
                    <th class="product-total">가격</th>
                    <th class="product-location">거래위치</th>
                    <th class="product-remove">삭제</th>
                  </tr>
               
              </table>
            </div>
          </form>
        </div>
        </c:when>
        <c:otherwise>
        <div class="row">
          	<div class="col-md-6">
          	<h2 class="h3 mb-3 text-black">로그인하고 이용해주세요!</h2>
          	<div class="row mb-5">
          		<button class="btn btn-primary" onclick="location.href='${path}/pages/user/login.jsp'">로그인하기</button>
          	</div>
          	</div>
          	</div>
        </c:otherwise>

</c:choose>
      </div>
    </div>

    
  </div>

  <script src="${path}/js/jquery-3.3.1.min.js"></script>
  <script src="${path}/js/jquery-ui.js"></script>
  <script src="${path}/js/popper.min.js"></script>
  <script src="${path}/js/bootstrap.min.js"></script>
  <script src="${path}/js/owl.carousel.min.js"></script>
  <script src="${path}/js/jquery.magnific-popup.min.js"></script>
  <script src="${path}/js/aos.js"></script>

  <script src="${path}/js/main.js"></script>
    
  </body>
</html>
<jsp:include page="${path}/pages/common/footer.jsp"/>