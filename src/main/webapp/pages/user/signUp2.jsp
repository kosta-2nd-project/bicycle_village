<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp"/>
 <!DOCTYPE html>
<html lang="en">
 
  <body>
  
  <div class="site-wrap">
  <div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0"><a href="/home.jsp">Home</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">회원가입</strong></div>
        </div>
      </div>
    </div>
    
    <div class="site-section">
      <div class="container">

<c:choose>
<c:when test="${not empty loginUser && UserStatus!=0}">
	<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/front">
			<input type="hidden" name="key" value = "user" /> 
			<input type="hidden" name="methodName" value = "changeStatus" /> 

        <div class="row">
          <div class="col-md-6 mb-5 mb-md-0">
            <h2 class="h3 mb-3 text-black">기존 정보 조회</h2>
            <div class="p-3 p-lg-5 border">
              
              <div class="form-group row">
                <div class="col-md-6">
                	<h4 for="userId" class="text-black">아이디:${loginId}</h4>
                </div>
              </div>
              
              <div class="form-group row">
                <div class="col-md-6">
                  <h4 class="text-black">이름:${loginName}</h4>
                </div>
                <div class="col-md-6">
                  <h4 class="text-black">닉네임:${nickName}</h4>
                </div>
              </div>

              <div class="form-group row">
                <div class="col-md-12">
                  <h4 class="text-black">전화번호:${loginTel}</h4>
                </div>
              </div>
              
              <div class="form-group row">
                <div class="col-md-12">
                <h4 class="text-black">생년월일:${loginBirth}</h4>
                </div>
              </div>

              <div class="form-group row">
                <div class="col-md-12">
                  <h4 class="text-black">이메일:${loginEmail}</h4>
                </div>
              </div>
              
              <div class="form-group row">
					<div class="col-md-12">
						<h4 class="text-black">성별:${loginGender}</h4>
					</div>
				</div>
              
              <div class="Input">
						<button type="submit" class="btn btn-primary">회원가입</button>
				</div>

            </div>
          </div>
        </div>
      </div>
    </div>

</form>
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