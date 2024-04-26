<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp"/>
<!DOCTYPE html>
<html lang="en">
  
  <body>
  
  <div class="site-wrap">

	<div class="bg-light py-3">
		<div class="container">
			<div class="row">
				<div class="col-md-12 mb-0"><a href="/index.jsp">Home</a> <span class="mx-2 mb-0">/</span> <a href="myPage.jsp">마이페이지</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">회원 탈퇴</strong></div>
			</div>
		</div>
	</div>	
	
		<div class="site-section">
		<div class="container">
		
		<c:choose>
			<c:when test="${not empty loginUser && UserStatus==0}">
				
					<div class="row mb-5">
          			<div class="col-md-12">
           			 <div class="border p-4 rounded" role="alert">
              	
					      <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/front">
							<input type="hidden" name="key" value = "user" />
							<input type="hidden" name="methodName" value = "unregist" />
							${loginName}님 정말로 탈퇴하시겠습니까?
							
							<button type="submit" class="btn btn-primary btn-sm btn-block">회원 탈퇴</button>
						</form>
			
            </div>
          </div>
        </div>
				</div>
				</div>
			</c:when>
			
		<c:otherwise>
			<div class="row">
          	<div class="col-md-6">
          	<h2 class="h3 mb-3 text-black">로그인하고 이용해주세요!</h2>
          	<div class="row mb-5">
          		<button class="btn btn-primary" onclick="location.href='login.jsp'">로그인하기</button>
          	</div>
          	</div>
          	</div>
		</c:otherwise>
		</c:choose>
	</div>
	</div>
	</div>
  </div>

  <script src="../js/jquery-3.3.1.min.js"></script>
  <script src="../js/jquery-ui.js"></script>
  <script src="../js/popper.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>
  <script src="../js/owl.carousel.min.js"></script>
  <script src="../js/jquery.magnific-popup.min.js"></script>
  <script src="../js/aos.js"></script>

  <script src="../js/main.js"></script>
    
  </body>
  <jsp:include page="../common/footer.jsp"/>
</html>
          