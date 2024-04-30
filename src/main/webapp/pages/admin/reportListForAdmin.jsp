<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="${path}/pages/common/header.jsp"/>
<html>
<head>
    <title>자전거마을 관리자</title>
    <script src="${path}/js/jquery-3.3.1.min.js"></script>



    <script></script>
</head>
<body>

<div class="insert">
    <form method="POST" onsubmit="return false;" enctype="multipart/form-data">
        <input type="file" onchange="addFile(this);" multiple />
        <div class="file-list"></div>
    </form>
</div>



</body>
</html>
<jsp:include page="${path}/pages/common/footer.jsp"/>