<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>Shoppers &mdash; Colorlib e-Commerce Template</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700">
    <link rel="stylesheet" href="/fonts/icomoon/style.css">

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/magnific-popup.css">
    <link rel="stylesheet" href="/css/jquery-ui.css">
    <link rel="stylesheet" href="/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/css/owl.theme.default.min.css">


    <link rel="stylesheet" href="/css/aos.css">

    <link rel="stylesheet" href="/css/style.css">

    <script src="${path}/js/jquery-3.3.1.min.js"></script>
    <script>
      $(function () {
        alarmCnt();
        function alarmCnt() {
          $.ajax({
            url: "rest",
            type: "post",
            dataType: "json",
            data: {key: "alarm", methodName: "countAlarm"},
            success: function (result) {
              let cnt;
              if(result !== 0) {
                cnt = result;
              }
              $(".count").html(cnt);
            },
            error: function (err) {
              console.log(err + " error");
            }
          })//ajax end
        }//alarmCnt end
      })//ready end
    </script>
    
  </head>
  <body>
  
  <div class="site-wrap">
    <header class="site-navbar" role="banner">
      <div class="site-navbar-top">
        <div class="container">
          <div class="row align-items-center">

            <div class="col-6 col-md-4 order-2 order-md-1 site-search-icon text-left">
              <form action="" class="site-block-top-search">
                <span class="icon icon-search2"></span>
                <input type="text" class="form-control border-0" placeholder="Search">
              </form>
            </div>

            <div class="col-12 mb-3 mb-md-0 col-md-4 order-1 order-md-2 text-center">
              <div class="site-logo">
                <a href="/index.jsp" class="js-logo-clone">자전거 마을</a>
              </div>
            </div>

            <div class="col-6 col-md-4 order-3 order-md-3 text-right">
              <div class="site-top-icons">
                <ul>
                
                <c:choose> 
                
                <c:when test="${not empty loginUser && UserStatus ==0}">
	
	     				<li><a>${loginName}님 로그인 중</a>
	      				<li><a href="${pageContext.request.contextPath}/front?key=user&methodName=logout" class="btn">Logout</a></li>
	     				<li><a href="${path}/pages/user/myPage.jsp"><span class="icon icon-person"></span></a></li>
                  		<li><a href="${path}/alarm.jsp"><span class="icon icon-heart-o"></span><span class="count"></span></a></li>
                 
    			 </c:when>
    			 
    			 <c:otherwise>
                  <li><a href="${path}/pages/user/login.jsp"><span class="icon icon-person"></span></a></li>
                  <li><a href="${path}/pages/user/login.jsp"><span class="icon icon-heart-o"></span></a></li>
                  </c:otherwise>
                  
                  </c:choose>  
                                     
                  <li class="d-inline-block d-md-none ml-md-0"><a href="#" class="site-menu-toggle js-menu-toggle"><span class="icon-menu"></span></a></li>
                </ul>
              </div> 
            </div>

          </div>
        </div>
      </div> 
      <nav class="site-navigation text-right text-md-center" role="navigation">
        <div class="container">
          <ul class="site-menu js-clone-nav d-none d-md-block">

            <li><a href="${path}/index.jsp">홈</a></li>
            <li><a href="${path}/front?key=board&methodName=selectAllFreeBoard">자유 게시판</a></li>
            <li><a href="${path}/front?key=board&methodName=selectAllInfoBoard">정보 게시판</a></li>
            <li><a href="${path}/front?key=board&methodName=selectAllTradeBoard">거래 게시판</a></li>

            <li><a href="#">신고 게시판</a></li>
            <li><a href="#">공지사항</a></li>
          </ul>
        </div>
      </nav>
    </header>

  </div>

  </body>
</html>