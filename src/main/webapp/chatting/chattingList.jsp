<%--
  Created by IntelliJ IDEA.
  User: asso0
  Date: 2024-05-01
  Time: 오전 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        $(function (){
            function chattingList(){
                $.ajax({
                    url:"/rest?key=chatting&methodName=findChatList",
                    type: "post",
                    dataType : "json",
                    success: function (result){

                    }
                })
            }
        })
    </script>
</head>
<body>

</body>
</html>
