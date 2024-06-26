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
          <div class="col-md-12 mb-0"><a href="${path}/index.jsp">Home</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">로그인</strong></div>
        </div>
      </div>
      </div>
      
          <div class="site-section">
      <div class="container">

<c:choose>
	<c:when test="${empty loginUser || UserStatus!=0}">
      
      <div class="row mb-5">
          <div class="col-md-12">
            <div class="border p-4 rounded" role="alert">
              계정이 없다면? <a href="signUp.jsp">회원가입</a>
            </div>
          </div>
        </div>
  
        <div class="row">
          <div class="col-md-6 mb-5 mb-md-0">
          	<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/front">
			<input type="hidden" name="key" value = "user" /> 
			<input type="hidden" name="methodName" value = "login" />  
          
            <h2 class="h3 mb-3 text-black">로그인</h2>
            <div class="p-3 p-lg-5 border">
             
              <div class="form-group row">
                <div class="col-md-12">
                  <label for="userId" class="text-black"> 아이디 </label>
                  <input type="text" class="form-control" id="userId" name="userId">
                </div>
                <div class="col-md-12">
                  <label for="pwd" class="text-black"> 비밀번호 </label>
                  <input type="password" class="form-control" id="pwd" name="pwd">
                </div>
              </div>
              <div class="Input">
						<button type="submit" class="btn btn-primary">로그인</button>
				</div>
            </div>
            </form>
          </div>
 
        </div>
        </div>
        </div>
        
    </c:when>
    
    <c:otherwise>
    	<div class="row">
          <div class="col-md-6">
          <h2 class="h3 mb-3 text-black">${loginName}님 반갑습니다!</h2>
          </div>
          </div>
    </c:otherwise>
  </c:choose>

   
  </div>

  <script src="${path}/js/jquery-3.3.1.min.js"></script>
  <script src="${path}/js/jquery-ui.js"></script>
  <script src="${path}/js/popper.min.js"></script>
  <script src="${path}/js/bootstrap.min.js"></script>
  <script src="${path}/js/owl.carousel.min.js"></script>
  <script src="${path}/js/jquery.magnific-popup.min.js"></script>
  <script src="${path}/js/aos.js"></script>

  <script src="../js/main.js"></script>
    
  </body>
  <jsp:include page="../common/footer.jsp"/>
</html>
          