<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp"/>
<html>
<head>
    <title>자전거 마을</title>
    <script src="../../js/jquery-3.3.1.min.js"></script>
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
                    $(".reviewSeq").html("<p>" + data.reviewSeq + "</p>");
                    $(".reviewing").html("<p>" + data.reviewing.userSeq + "</p>");
                    $(".reviewer").html("<p>" + data.reviewer.userSeq + "</p>");
                    $(".board").html("<p>" + data.board.boardSeq + "</p>");
                    <%--http://localhost:8000/front?key=board&methodName=selectByTradeBoardSeq&boardSeq=155--%>
                    $(".board").html("<a href='${pageContext.request.contextPath}/front?key=board&methodName=selectByTradeBoardSeq&boardSeq=" + data.board.boardSeq + "'>" + "해당 게시글로 이동하기" + "</a>");
                    $(".boardSeq").html("<p>" + data.board.boardSeq + "</p>");
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

            $('#modifyBtn').click(function() {
                var reviewSeq = $('.reviewSeq p').text();
                var reviewing = $('.reviewing p').text();
                var reviewer = $('.reviewer p').text();
                var board = $('.boardSeq p').text();
                var date = $('.date p').text();
                var content = $('.content p').text();
                var rate = $('.rate p').text();

                var url = "/front?key=review&methodName=postDetailPage";
                url += "&reviewSeq=" + encodeURIComponent(reviewSeq);
                url += "&reviewing=" + encodeURIComponent(reviewing);
                url += "&reviewer=" + encodeURIComponent(reviewer);
                url += "&board=" + encodeURIComponent(board);
                url += "&date=" + encodeURIComponent(date);
                url += "&content=" + encodeURIComponent(content);
                url += "&rate=" + encodeURIComponent(rate);

                window.location.href = url;
            });

            $('#deleteBtn').click(function() {
                var reviewSeq = $('.reviewSeq p').text();

                // 서버에 삭제 요청을 보냅니다.
                $.ajax({
                    url: "rest?key=review&methodName=deleteReview&reviewSeq=" + reviewSeq, // 요청을 보낼 URL
                    type: "DELETE", // 요청 방식을 DELETE로 설정
                    dataType: "text", // 응답 데이터 타입
                    success: function(data) {
                        console.log(data);
                        // 삭제가 성공적으로 이루어졌을 때 실행되는 함수
                        // 성공 메시지를 출력하고 페이지를 뒤로 이동합니다.
                        alert("리뷰가 삭제되었습니다.");
                        history.back();
                    },
                    error: function (xhr, status, error) {
                        // 오류가 발생했을 때 실행되는 함수
                        console.error(xhr, status, error);
                        alert("리뷰 삭제 중 오류가 발생했습니다.");
                    }
                });
            });

        });
    </script>
</head>

<body>
<div class="review" style="width: 400px; margin: 0 auto;">
    <div class="reviewSeq" style='display: inline-block;'></div>
    <div class="reviewing" style='display: inline-block;'></div>
    <div class="reviewer" style='display: inline-block;'></div>
    <div class="board" style='display: inline-block;' ></div>
    <div class="boardSeq" style="display: none;"></div>
    <div class="date" style="margin-right: 10px"></div>
    <div class="content" style="width: 80%; margin: 0 auto;"></div>
    <div class="rate" style="width: 80%; margin: 0 auto;"></div>

    <button id="modifyBtn" class="btn btn-primary">리뷰 수정</button>
    <button id="deleteBtn" class="btn btn-primary">리뷰 삭제</button>

</div>
</body>
</html>
<jsp:include page="../common/footer.jsp"/>
