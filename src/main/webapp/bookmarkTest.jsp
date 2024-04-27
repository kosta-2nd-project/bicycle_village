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
                data: {key: "bookmark", methodName: "addBookmark", BookmarkSeq:0, userSeq:4, boardSeq:20},
                success: function (result) {
                   
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
