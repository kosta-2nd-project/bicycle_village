<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp"/>
<html>
<head>
    <title>자전거 마을</title>
    <script src="../../js/jquery-3.3.1.min.js"></script>>
    <script>
        $(document).ready(function() {
            <c:choose>
            <c:when test="${reviewing == null}">
                // 페이지가 로드될 때 POST 요청을 시작합니다.
                $.ajax({
                    url: "rest", // 요청을 보낼 URL
                    type: "POST", // 요청 방식을 POST로 설정
                    dataType: "json", // 응답 데이터 타입
                    data: {
                        // 전송할 데이터를 여기에 추가합니다.
                        key: "review",
                        methodName: "getList",
                        reviewer: "${reviewer}"
                    },
                    success: function (data) {
                        for (var i = 0; i < data.length; i++) {
                            var review = data[i];
                            var reviewDiv = $("<div class='review'>");
                            reviewDiv.append("<div class='reviewing' style='display: inline-block;'><p>reviewing Seq: " + review.reviewing.userSeq + "</p></div>");
                            reviewDiv.append("<div class='reviewer' style='display: inline-block;'><p>reviewer Seq: " + review.reviewer.userSeq + "</p></div>");
                            reviewDiv.append("<div class='board' style='display: inline-block;'><p>Board Seq: " + review.board.boardSeq + "</p></div>");
                            reviewDiv.append("<div class='content' style='display: inline-block;'><p>Content: " + review.reviewContent + "</p></div>");
                            reviewDiv.append("<div class='rate' style='display: inline-block;'><p>Rate: " + review.reviewScore + "</p></div>");
                            reviewDiv.append("<div class='date' style='display: inline-block;'><p>Date: " + review.reviewDate + "</p></div>");
                            $("#output").append(reviewDiv);
                        }

                    },
                    error: function (xhr, status, error) {
                        // 오류가 발생했을 때 실행되는 함수
                        console.error(xhr, status, error);
                        $("#output").html("<p>Error occurred while fetching data.</p>");
                    }
                });
            </c:when>
            <c:otherwise>
                // 페이지가 로드될 때 POST 요청을 시작합니다.
                $.ajax({
                    url: "rest", // 요청을 보낼 URL
                    type: "POST", // 요청 방식을 POST로 설정
                    dataType: "json", // 응답 데이터 타입
                    data: {
                        // 전송할 데이터를 여기에 추가합니다.
                        key: "review",
                        methodName: "getReview",
                        reviewing: "${reviewing}"
                    },
                    success: function (data) {
                        var review = data[i];
                        var reviewDiv = $("<div class='review'>");
                        reviewDiv.append("<div class='reviewing' style='display: inline-block;'><p>reviewing Seq: " + review.reviewing.userSeq + "</p></div>");
                        reviewDiv.append("<div class='reviewer' style='display: inline-block;'><p>reviewer Seq: " + review.reviewer.userSeq + "</p></div>");
                        reviewDiv.append("<div class='board' style='display: inline-block;'><p>Board Seq: " + review.board.boardSeq + "</p></div>");
                        reviewDiv.append("<div class='content' style='display: inline-block;'><p>Content: " + review.reviewContent + "</p></div>");
                        reviewDiv.append("<div class='rate' style='display: inline-block;'><p>Rate: " + review.reviewScore + "</p></div>");
                        reviewDiv.append("<div class='date' style='display: inline-block;'><p>Date: " + review.reviewDate + "</p></div>");
                        $("#output").append(reviewDiv);
                    },
                    error: function (xhr, status, error) {
                        // 오류가 발생했을 때 실행되는 함수
                        console.error(xhr, status, error);
                        $("#output").html("<p>Error occurred while fetching data.</p>");
                    }
                });
            </c:otherwise>
            </c:choose>
        });
    </script>
</head>
<body>
리뷰 리스트로 보기 페이지
<div id="output">
</div>
</body>
</html>
<jsp:include page="../common/footer.jsp"/>
