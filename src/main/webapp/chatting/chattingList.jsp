<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>채팅 목록</title>
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }
        h2 {
            color: #333;
            margin-bottom: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 24px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h2>고객 리스트!</h2>
<table id="chatListTable" cellspacing="0">
    <thead>
    <tr>
        <th>판매자</th>
        <th>액션</th>
    </tr>
    </thead>
    <tbody>
    <!-- AJAX로 채워질 내용 -->
    </tbody>

</table>

<script type="text/javascript">
    $(function (){
        let userId ='${loginId}'; // 현재 로그인한 사용자 ID 가져오기, 서버사이드 코드가 필요한 부분입니다.
            console.log(userId+"채팅리스트유저아이디")
        let boardWriter= '${board.userDTO.userId}';
        console.log(boardWriter+"판매자")


            function chattingList() {
            $.ajax({
                        url: "/rest?key=chatting&methodName=findUrlByUSerId",
                            type: "post",
                            dataType: "json",
                            data: {
                            userId: userId,
                        },
                        success: function(result) {
                            console.log("Communication Successful");

                            var $tableBody = $('#chatListTable').find('tbody');

                            $.each(result, function(index, chat) {
                                // 채팅 데이터를 기반으로 테이블 행 생성
                        var $row = $('<tr></tr>');
                        $row.append($('<td></td>').text(chat.sellerId)); // 채팅 제목(혹은 다른 원하는 속성)
                        $row.append($('<td></td>').html('<a href="' + chat.roomUrl + '">채팅 들어가기</a>')); // 채팅 URL
                        $tableBody.append($row);
                    });
                },
                error: function(err) {
                    console.log("Error Occurred");
                }
            });
                $.ajax({
                    url: "/rest?key=chatting&methodName=findRoomIdByUserId",
                    type: "post",
                    dataType: "json",
                    data: {
                        userId: userId
                    },
                    success: function(result) {
                        console.log("Communication Successful");

                        var $tableBody = $('#chatListTable').find('tbody');

                        $.each(result, function(index, chat) {
                            // 채팅 데이터를 기반으로 테이블 행 생성
                            var $row = $('<tr></tr>');
                            $row.append($('<td></td>').text(chat.sellerId)); // 채팅 제목(혹은 다른 원하는 속성)
                            $row.append($('<td></td>').html('<a href="' + chat.roomUrl + '">채팅 들어가기</a>')); // 채팅 URL
                            $tableBody.append($row);
                        });
                    },
                    error: function(err) {
                        console.log("Error Occurred");
                    }
                });
        }


        chattingList(); // 채팅 목록 가져오기 함수 호출
    });
</script>
</body>
</html>
