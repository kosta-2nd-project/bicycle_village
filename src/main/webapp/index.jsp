<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="pages/common/header.jsp"/>
<!DOCTYPE html>
<html lang="en">

<head>
<script src="${path}/js/jquery-3.3.1.min.js"></script>
<script>
$(function(){
	function selectBest(){
		$.ajax({
			url:"${path}/rest",
			type:"post",
			dataType:"json",
			data:{key:"indexpost",methodName:"selectBest"},
			success:function(result){
				let str="";
				$.each(result,function(index,item){
					str+=`<div class="item">`;
	                str+=`<div class="block-4 text-center">`;
	                str+=`<figure class="block-4-image">`;
	                if(`\${item.imageName}` !== 'undefined'){
						str+=`<td><img class ="img-fluid" width="200px" height="200px"  src="<%= request.getContextPath() + "/file-servlet"+ "?fname=${item.boardSeq}/${item.imageName}" %>"></td>`;
					}else{
						str+=`<td>이미지 없음</td>`;
					}
	                str+=`</figure>`;
	                str+=`<div class="block-4-text p-4">`;
	                str+=`<h3><a href="/front?key=board&methodName=selectByTradeBoardSeq&boardSeq=\${item.boardSeq}">\${item.boardName}</a></h3>`;
	               	str+=`<p class="mb-0"></p>`;
	                str+=`<p class="text-primary font-weight-bold">가격: \${item.goodsPrice}</p>`;
	                str+=`</div>`;
	                str+=`</div>`;
	              	str+=`</div>`;
				});
				$(".nonloop-block-3").html(str);
			},
			error:function(err){
				
			}
		});
	}//selectBest End
	
	selectBest();
})
</script>
</head>
  
  <body>
  
  <div class="site-wrap">

    <div class="site-blocks-cover" style="background-image: url(images/home.jpg);" data-aos="fade">
      <div class="container">
        <div class="row align-items-start align-items-md-center justify-content-end">
          <div class="col-md-5 text-center text-md-left pt-5 pt-md-0">
            <h1 class="mb-2">자전거 전문 <br> 중고거래 플랫폼</h1>
            <div class="intro-text text-center text-md-left">
              <p class="mb-4">자전거 마을에서 자전거와 관련 물품을 <br> 중고거래하고 정보를 공유하세요. </p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="site-section block-3 site-blocks-2 bg-light">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-md-7 site-section-heading text-center pt-4">
            <h2>최신 게시글</h2>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <div class="nonloop-block-3 owl-carousel">
             
              
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

  <script src="js/jquery-3.3.1.min.js"></script>
  <script src="js/jquery-ui.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/aos.js"></script>

  <script src="js/main.js"></script>
    
  </body>
  <jsp:include page="pages/common/footer.jsp"/>
</html>