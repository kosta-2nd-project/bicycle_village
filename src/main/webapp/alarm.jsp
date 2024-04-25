<%--
  Created by IntelliJ IDEA.
  User: Kosta
  Date: 2024-04-24
  Time: 오후 7:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script>
        $(function(){
            $.ajax({
                url: "rest",
                type: "post",
                dataType: "json",
                data: {key: "alarm", methodName: "insert"},
                success: function (result) {
                    if(result === 0) {
                        alert("alarm insert fail");
                    } else {
                        alert("alarm inset success");
                    }
                },
                error: function (err) {
                    console.log(err + " error");
                }
            })//ajax end
        })//ready end
    </script>
</head>
<body>

</body>
</html>
