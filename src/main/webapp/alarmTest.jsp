<%--
  Created by IntelliJ IDEA.
  User: Kosta
  Date: 2024-04-30
  Time: 오후 5:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>alarm & follow</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
    <!--<link rel="stylesheet" href="/resources/demos/style.css">-->
    <style>
        p a:link, a:visited {
            text-decoration: none;
        }
        input[type='']
    </style>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#tabs" ).tabs();
            alarmList();
            followList();

            //알림 리스트 띄우기
            function alarmList() {
                $.ajax({
                    url: "rest",
                    type: "post",
                    dataType: "json",
                    data: {key: "alarm", methodName: "selectAll"},
                    success: function (result) {
                        if(result != null) {
                            let str = "";
                            $.each(result, function (index, list) {
                                str += `<p><a href="#" class = "alarm" data-alarm-seq="${'${list.alarmSeq}'}">${"${list.alarmContent}"}</a></p>`;
                            });
                            $("#tabs-1 > p").html(str);
                        }
                    },
                    error: function (err) {
                        console.log(err + " error");
                    }
                })//ajax end
            }//function end
            /*$.ajax({
                url: "rest",
                type: "post",
                dataType: "json",
                data: {key: "alarm", methodName: "selectAll"},
                success: function (result) {
                    if(result != null) {
                        let str = "";
                        $.each(result, function (index, list) {
                            str += `<p><a href="#" class = "alarm" data-alarm-seq="${'${list.alarmSeq}'}">${"${list.alarmContent}"}</a></p>`;
                        });
                        $("#tabs-1 > p").html(str);
                    }
                },
                error: function (err) {
                    console.log(err + " error");
                }
            })//ajax end*/

            //알림 클릭할 때
            $(document).on("click", ".alarm", function () {
                $.ajax({
                    url: "rest",
                    type: "post",
                    dataType: "json",
                    data: {key: "alarm", methodName: "updateAlarm", alarmSeq: this.getAttribute("data-alarm-seq")},
                    success: function (map) {
                        console.log("result success!")
                        if(map.result === 0) {
                            alert("update fail");
                        } else {
                            alert("update success");
                            let url = map.url;
                            console.log(url);
                            location.href = "/" + url;
                        }
                    },
                    error: function (err) {
                        console.log(err + "error");
                    }
                })//ajax end
            })//click end

            //팔로우 리스트 띄우기
            function followList() {
                $.ajax({
                    url: "rest",
                    type: "post",
                    dataType: "json",
                    data: {key: "follow", methodName: "selectAll"},
                    success: function (result) {
                        if(result != null) {
                            let str = "";
                            $.each(result, function (index, list) {
                                str += `<p><a href="#" class="follow" data-follow="${'${list.userId}'}">${"${list.nickName}"}(${"${list.userId}"})님</a></p>`;
                            });
                            $("#tabs-2 > p").html(str);
                        }
                    },
                    error: function (err) {
                        console.log(err + " error");
                    }
                })//ajax end
            }
            <%--$.ajax({--%>
            <%--    url: "rest",--%>
            <%--    type: "post",--%>
            <%--    dataType: "json",--%>
            <%--    data: {key: "follow", methodName: "selectAll"},--%>
            <%--    success: function (result) {--%>
            <%--        if(result != null) {--%>
            <%--            let str = "";--%>
            <%--            $.each(result, function (index, list) {--%>
            <%--                str += `<p><a href="#" class="follow" data-follow="${'${list.userId}'}">${"${list.nickName}"}(${"${list.userId}"})님</a></p>`;--%>
            <%--            });--%>
            <%--            $("#tabs-2 > p").html(str);--%>
            <%--        }--%>
            <%--    },--%>
            <%--    error: function (err) {--%>
            <%--        console.log(err + " error");--%>
            <%--    }--%>
            <%--})//ajax end--%>

            //팔로우 클릭 할 때
            $(document).on("click", ".follow", function () {
                let text =`<span><input type="button" class="btn" value="취소"><input type="button" class="del" value="팔로우 취소"></span>`
                $(this).closest(".fol > p").append(text);
                // $(this).remove();
            })//click end

            //취소 버튼 클릭할 때
            $(document).on("click", ".btn" , function () {
                $(this).closest("span").remove();
            })//click end

            //팔로우 취소 버튼 클릭할 때
            $(document).on("click",".del", function () {
                let followId = $(this).closest("span").siblings("a").attr("data-follow");
                $.ajax({
                    url: "rest",
                    type: "post",
                    dataType: "json",
                    data: {key: "follow", methodName: "delFollow", followId: followId},
                    success: function (result) {
                        if(result === 0){
                            // console.log("fail");
                        } else {
                            // console.log("success!");
                            followList();
                        }
                    },
                    error: function (err) {
                        console.log(err + "error");
                    }
                });//ajax end
            });//click end
        } )//ready end
    </script>
</head>
<body>
<div id="tabs">
    <ul>
        <li><a href="#tabs-1">알림</a></li>
        <li><a href="#tabs-2">팔로우</a></li>
    </ul>
    <div id="tabs-1"><p></p></div>
    <div id="tabs-2"><p class="fol"></p></div>
</div>
</body>
</html>