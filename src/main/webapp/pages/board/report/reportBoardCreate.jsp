<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="${path}/pages/common/header.jsp"/>
<html>
<head>
    <title>자전거마을 관리자</title>
    <script src="${path}/js/jquery-3.3.1.min.js"></script>

    <script src="${path}/js/multiUpload.js"></script>
    <link rel="stylesheet" href="${path}/css/board.css">
    <script src="${path}/js/board.js"></script>
</head>
<body>
<%--게시글 작성--%>
<div class="board">
    <form name="writeForm" method="post"
          action="${path}/front?key=board&methodName=insert"
          onSubmit='return checkValid()' enctype="multipart/form-data">
        <input type="hidden" name="category" value="REPORT">
        <input type="hidden" name="board_count" value="0">
        <input type="hidden" name="goods_price" value="0">
        <input type="hidden" name="product_seq" value="0">
        <input type="hidden" name="is_seen" value="AVAILABLE">
        <input type="hidden" name="board_addr" value="">
        <input type="hidden" name="user_seq" value="${user_seq}">
        <%--제목--%>
        <div class="title">
            <label for="board_name">글 제목: </label>
            <input type="text" size="50" name="board_name" id="board_name"></input>
        </div>
        <%--멀티 업로드--%>
        <div class="insert">
            <form method="POST" onsubmit="return false;" enctype="multipart/form-data">
                <label for="multiUpload">pdf, gif, jpeg, png, bmp, tif, haansofthwp, x-hwp 확장자 파일 5개까지 첨부 가능합니다.</label>
<%--                <input id="multiUpload" type="file" onchange="addFile(this);" multiple/>--%>
                <input id="multiUpload" name="file" type="file" onchange="addFile(this);" multiple/>
            <%--['application/pdf', 'image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif', 'application/haansofthwp', 'application/x-hwp'];--%>
                <div class="file-list"></div>
            </form>
        </div>
        <%--내용--%>
        <div class="content">
            <label for="board_content">내용: </label>
            <textarea name="board_content" id="board_content" cols="50" rows="10"></textarea>
        </div>
        <div ><input type=submit value="게시글 등록" id="submit"></div>
    </form>
</div>
</body>
</html>
<jsp:include page="${path}/pages/common/footer.jsp"/>