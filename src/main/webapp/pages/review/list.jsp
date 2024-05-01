<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp"/>
<html>
<head>
    <title>자전거 마을</title>
    <script src="../../js/jquery-3.3.1.min.js"></script>
    <script defer>
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
                            // reviewDiv.append("<div class='reviewing' style='display: inline-block;'><p>reviewing Seq: " + review.reviewing.userSeq + "</p></div>");
                            // reviewDiv.append("<div class='reviewer' style='display: inline-block;'><p>reviewer Seq: " + review.reviewer.userSeq + "</p></div>");
                            reviewDiv.append("<div class='board' style='display: inline-block;'>"+"<a href='${pageContext.request.contextPath}/front?key=board&methodName=selectByTradeBoardSeq&boardSeq=" + review.board.boardSeq + "'>" + "해당 게시글로 이동하기" + "</a>" +"</div>&nbsp&nbsp&nbsp");
                            reviewDiv.append("<div class='review' style='display: inline-block;'>"+"<a href='${pageContext.request.contextPath}/front?key=review&methodName=detailPage&reviewSeq=" + review.reviewSeq + "'>" + "해당 리뷰로 이동하기" + "</a>" +"</div></br>");
                            reviewDiv.append("<div class='content' style='display: inline-block; margin: 0;'><p style='margin: 0;'>후기: " + review.reviewContent + "</p></div>");
                            reviewDiv.append("<div class='rate' style='display: inline-block; margin: 0;'><p style='margin: 0;'>평점: " + review.reviewScore + "</p></div>");
                            reviewDiv.append("<div class='date' style='display: inline-block; margin: 0;'><p style='margin: 0;'>작성일: " + review.reviewDate + "</p></div>");
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
                        // reviewDiv.append("<div class='reviewing' style='display: inline-block;'><p>reviewing Seq: " + review.reviewing.userSeq + "</p></div>");
                        // reviewDiv.append("<div class='reviewer' style='display: inline-block;'><p>reviewer Seq: " + review.reviewer.userSeq + "</p></div>");
                        reviewDiv.append("<div class='board' style='display: inline-block;'>"+"<a href='${pageContext.request.contextPath}/front?key=board&methodName=selectByTradeBoardSeq&boardSeq=" + review.board.boardSeq + "'>" + "해당 게시글로 이동하기" + "</a>" +"</div>&nbsp&nbsp&nbsp");
                        reviewDiv.append("<div class='review' style='display: inline-block;'>"+"<a href='${pageContext.request.contextPath}/front?key=review&methodName=detailPage&reviewSeq=" + review.reviewSeq + "'>" + "해당 리뷰로 이동하기" + "</a>" +"</div></br>");
                        reviewDiv.append("<div class='content' style='display: inline-block; margin: 0;'><p style='margin: 0;'>후기: " + review.reviewContent + "</p></div>");
                        reviewDiv.append("<div class='rate' style='display: inline-block; margin: 0;'><p style='margin: 0;'>평점: " + review.reviewScore + "</p></div>");
                        reviewDiv.append("<div class='date' style='display: inline-block; margin: 0;'><p style='margin: 0;'>작성일: " + review.reviewDate + "</p></div>");
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
<div id="output" style="width: 400px; margin: 0 auto;">
</div>
</body>
</html>
<jsp:include page="../common/footer.jsp"/>
