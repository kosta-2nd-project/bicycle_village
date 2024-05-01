<%--
  Created by IntelliJ IDEA.
  User: snowwhite
  Date: 2024-05-01
  Time: 오후 6:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${loginName}님 알림 내용</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700">
    <link rel="stylesheet" href="/fonts/icomoon/style.css">

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/magnific-popup.css">
    <link rel="stylesheet" href="/css/jquery-ui.css">
    <link rel="stylesheet" href="/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/css/owl.theme.default.min.css">


    <link rel="stylesheet" href="/css/aos.css">

    <link rel="stylesheet" href="/css/style.css">

    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
    <style>
        p a:link, a:visited {
            text-decoration: none;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#tabs" ).tabs();
            alarmList();
            followList();
            let status = false; //팔로우 취소 버튼을 제어하기 위한 변수

            //알림 리스트 띄우기
            function alarmList() {
                $.ajax({
                    url: "rest",
                    type: "post",
                    dataType: "json",
                    data: {key: "alarm", methodName: "selectAll"},
                    success: function (result) {
                        let str = "";
                        if(result.length === 0) {
                            str += "알림이 없습니다."
                        } else {
                            $.each(result, function (index, list) {
                                str += `<p><a href="#" class = "alarm" data-alarm-seq="${'${list.alarmSeq}'}">${"${list.alarmContent}"}</a></p>`;
                            });
                        }
                        $("#tabs-1 > .mb-0").html(str);
                    },
                    error: function (err) {
                        console.log(err + " error");
                    }
                })//ajax end
            }//alarmList end

            //알림 클릭할 때
            $(document).on("click", ".alarm", function () {
                $.ajax({
                    url: "rest",
                    type: "post",
                    dataType: "json",
                    data: {key: "alarm", methodName: "updateAlarm", alarmSeq: this.getAttribute("data-alarm-seq")},
                    success: function (map) {
                        if(map.result === 0) {
                            alert("update fail");
                        } else {
                            let url = map.url;
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
                        let str = "";
                        if(result.length === 0) {
                            str += "팔로우한 유저가 없습니다."
                        } else {
                            $.each(result, function (index, list) {
                                str += `<p><a href="#" class="follow" data-follow="${'${list.userId}'}">${"${list.nickName}"}(${"${list.userId}"})님</a></p>`;
                            });
                        }
                        $("#tabs-2 > .mb-0").html(str);
                    },
                    error: function (err) {
                        console.log(err + " error");
                    }
                })//ajax end
            }//followList end

            //팔로우 클릭 할 때
            $(document).on("click", ".follow", function () {
                let text =`<span><input type="button" class="btn btn-primary" value="팔로우취소"><input type="button" class="btn btn-primary" value="취소"></span>`
                let followClick = $(this).closest(".fol > p").append(text);
                // #tabs-2 > p > p > span
                clicked("#tabs-2 > p > p > span");
                status = true;
            })//click end

            //이미 클릭한 팔로우를 한번 더 클릭했을 때
            function clicked(followClick) {
                if(status) {
                    $(followClick).siblings("span").remove();
                }
            }//clicked end

            //취소 버튼 클릭할 때
            $(document).on("click", "[value='취소']" , function () {
                $(this).closest("span").remove();
            })//click end

            //팔로우 취소 버튼 클릭할 때
            $(document).on("click","[value='팔로우 취소']", function () {
                let followId = $(this).closest("span").siblings("a").attr("data-follow");
                $.ajax({
                    url: "rest",
                    type: "post",
                    dataType: "json",
                    data: {key: "follow", methodName: "delFollow", followId: followId},
                    success: function (result) {
                        if(result !== 0){
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
        <li><a href="#tabs-1" class="btn btn-primary">알림</a></li>
        <li><a href="#tabs-2" class="btn btn-primary">팔로우</a></li>
    </ul>
    <div id="tabs-1"><p class="mb-0"></p></div>
    <div id="tabs-2"><p class="fol mb-0"></p></div>
</div>
</body>
</html>
