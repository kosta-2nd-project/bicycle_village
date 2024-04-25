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
          <div class="col-md-12 mb-0"><a href="/home.jsp">Home</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">마이페이지</strong></div>
        </div>
      </div>
    </div>

    <div class="site-section">
      <div class="container">

	<c:choose>
		<c:when test="${not empty loginUser && UserStatus ==0}">
        <div class="row">
          <div class="col-md-6">
          <h2 class="h3 mb-3 text-black">${loginName}님 반갑습니다!</h2>
            <div class="row mb-5">
              <div class="col-md-6 mb-3 mb-md-0">
                <button class="btn btn-primary btn-sm btn-block" onclick="location.href='info.jsp'">회원정보 수정하기</button>
                <button class="btn btn-outline-primary btn-sm btn-block">찜 목록</button>
                <button class="btn btn-outline-primary btn-sm btn-block">팔로우 목록</button>
                <button class="btn btn-outline-primary btn-sm btn-block">작성 게시글 목록</button>
                <button class="btn btn-primary btn-sm btn-block">채팅 목록</button>
                <button class="btn btn-primary btn-sm btn-block">작성 리뷰 목록</button>
                <button class="btn btn-outline-primary btn-sm btn-block" onclick="location.href='unregist.jsp'">회원 탈퇴</button>
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