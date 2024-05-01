<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2024-04-30
  Time: 오전 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>자전거 마을</title>
    <script src="../../js/jquery-3.3.1.min.js"></script>

</head>
<body>
    <form action="rest" method="POST" style="width: 400px; margin: 0 auto;" >
        <input type="hidden" id="key" name="key" value="review"><br>
        <%
            String reviewSeq = (String) request.getAttribute("reviewSeq");
            String methodName = (reviewSeq != null && !reviewSeq.isEmpty()) ? "putReview" : "postReview";
        %>
        <input type="hidden" id="methodName" name="methodName" value="<%= methodName %>"><br>

        <input type="hidden" id="reviewSeq" name="reviewSeq" value="${reviewSeq}">
        <input type="hidden" id="reviewing" name="reviewing" value="${reviewing}">
        <input type="hidden" id="reviewer" name="reviewer" value="${reviewer}">
        <input type="hidden" id="boardSeq" name="boardSeq" value="${board}">

        <label for="content">후기:</label><br>
        <textarea id="content" name="content" style="width: 300px; height: 150px;">${content}</textarea><br>

        <label for="score">평점:</label><br>
<%--        <input type="text" id="score" name="score" value="${rate}">--%>
        <select id="score" name="score">
            <option value="0" ${rate == 'TERRIBLE' ? "selected" : ""}>TERRIBLE</option>
            <option value="1" ${rate == 'POOR' ? "selected" : ""}>POOR</option>
            <option value="2" ${rate == 'FAIR' ? "selected" : ""}>FAIR</option>
            <option value="3" ${rate == 'AVERAGE' ? "selected" : ""}>AVERAGE</option>
            <option value="4" ${rate == 'GOOD' ? "selected" : ""}>GOOD</option>
            <option value="5" ${rate == 'EXCELLENT' ? "selected" : ""}>EXCELLENT</option>
        </select>
        <br><br>

        <button type="submit">리뷰 작성</button>
    </form>
</body>
</html>
