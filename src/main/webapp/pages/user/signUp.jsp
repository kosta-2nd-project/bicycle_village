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
          <div class="col-md-12 mb-0"><a href="/index.jsp">Home</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">회원가입</strong></div>
        </div>
      </div>
    </div>
    
        <div class="site-section">
      <div class="container">

<c:choose>
<c:when test="${empty loginUser || UserStatus!=0}">
	<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/front">
			<input type="hidden" name="key" value = "user" /> 
			<input type="hidden" name="methodName" value = "signUp" /> 

        <div class="row">
          <div class="col-md-6 mb-5 mb-md-0">
            <h2 class="h3 mb-3 text-black">회원가입</h2>
            <div class="p-3 p-lg-5 border">
              
              <div class="form-group row">
                <div class="col-md-6">
                	<label for="userId" class="text-black">아이디 <span class="text-danger">*</span></label>
                  <input type="text" class="form-control" id="userId" name="userId">
                </div>
              </div>
              
              <div class="form-group row">
                <div class="col-md-6">
                  <label for="pwd" class="text-black">비밀번호 <span class="text-danger">*</span></label>
                  <input type="password" class="form-control" id="pwd" name="pwd">
                </div>
                <div class="col-md-6">
                  <label for="pwd2" class="text-black">비밀번호 재확인 <span class="text-danger">*</span></label>
                  <input type="password" class="form-control" id="pwd2" name="pwd2">
                </div>
              </div>
              
              <div class="form-group row">
                <div class="col-md-6">
                  <label for="name" class="text-black">이름 <span class="text-danger">*</span></label>
                  <input type="text" class="form-control" id="name" name="name">
                </div>
                <div class="col-md-6">
                  <label for="name2" class="text-black">닉네임 <span class="text-danger">*</span></label>
                  <input type="text" class="form-control" id="name2" name="name2">
                </div>
              </div>

              <div class="form-group row">
                <div class="col-md-12">
                  <label for="tel" class="text-black">전화번호 <span class="text-danger">*</span></label>
                  <input type="text" class="form-control" id="tel" name="tel">
                </div>
              </div>
              
              <div class="form-group row">
                <div class="col-md-12">
                <label for="birth" class="text-black">생년월일 <span class="text-danger">*</span></label>
                  <input type="date" class="form-control" id="birth" name="birth" placeholder="YYYY.MM.DD">
                </div>
              </div>

              <div class="form-group row">
                <div class="col-md-12">
                  <label for="email" class="text-black">이메일 </label>
                  <input type="email" class="form-control" id="email" name="email">
                </div>
              </div>
              
              <div>
					<div>
                      <input type="radio" id="woman" name="gender" value="woman">
                      <label for="woman">여</label>

                      <input type="radio" id="man" name="gender" value="man">
                      <label for="man">남</label>
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
          <h2 class="h3 mb-3 text-black">${loginName}님 반갑습니다!</h2>
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