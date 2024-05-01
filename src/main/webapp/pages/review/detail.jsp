<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp"/>
<html>
<head>
    <title>자전거 마을</title>
    <script src="../../js/jquery-3.3.1.min.js"></script>>
    <script>

        $(document).ready(function(){
            // 페이지가 로드될 때 POST 요청을 시작합니다.
            $.ajax({
                url: "rest", // 요청을 보낼 URL
                type: "POST", // 요청 방식을 POST로 설정
                dataType: "json", // 응답 데이터 타입
                data: {
                    // 전송할 데이터를 여기에 추가합니다.
                    key: "review",
                    methodName: "getReview",
                    reviewSeq: "${reviewSeq}"
                },
                success: function(data) {
                    console.log(data)
                    // 성공적으로 응답을 받았을 때 실행되는 함수
                    // 받은 데이터를 출력합니다.
                    $(".reviewing").html("<p>" + data.reviewing.userSeq + "</p>");
                    $(".reviewer").html("<p>" + data.reviewer.userSeq + "</p>");
                    // $(".board").html("<p>" + data.board.boardSeq + "</p>");
                    <%--http://localhost:8000/front?key=board&methodName=selectByTradeBoardSeq&boardSeq=155--%>
                    $(".board").html("<a href='${pageContext.request.contextPath}/front?key=board&methodName=selectByTradeBoardSeq&boardSeq=" + data.board.boardSeq + "'>" + "해당 게시글로 이동하기" + "</a>");
                    $(".content").html("<p>" + data.reviewContent + "</p>");
                    $(".rate").html("<p>" + data.reviewScore + "</p>");
                    $(".date").html("<p>" + data.reviewDate + "</p>");
                },
                error: function (xhr, status, error) {
                    // 오류가 발생했을 때 실행되는 함수
                    console.error(xhr, status, error);
                    $("#output").html("<p>Error occurred while fetching data.</p>");
                }
            });
        });
    </script>
</head>

<body>
<div class="review" style="width: 400px; margin: 0 auto;">
    <div class="reviewing" style='display: inline-block;'></div>
    <div class="reviewer" style='display: inline-block;'></div>
    <div class="board" style='display: inline-block;' ></div>
    <div class="date" style="margin-right: 10px"></div>
    <div class="content" style="width: 80%; margin: 0 auto;"></div>
    <div class="rate" style="width: 80%; margin: 0 auto;"></div>
</div>
</body>
</html>
<jsp:include page="../common/footer.jsp"/>
