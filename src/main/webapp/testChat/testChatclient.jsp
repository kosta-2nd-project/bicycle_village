<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Socket.io 채팅 클라이언트</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/4.1.2/socket.io.js"></script>
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>

    <style>
        form {
            position: fixed;
            bottom: 0;
            width: 100%;
            padding-top: 10px;
            padding-right: 20px;
            padding-left:10px ;
            box-sizing: border-box; /* 패딩을 너비에 포함 */
            height: 20%; /* 높이 20%로 조정 */
        }
        #message {
            padding-right: 5px;
            margin-right:4px;
            height: 70%; /* 입력 상자의 높이를 부모 요소의 70%로 설정 */
            width: 100%; /* 입력 상자의 너비를 부모 요소의 100%로 설정 */
        }
        #textmessage {
            padding: 20px;
            overflow-y: scroll; /* 내용이 많을 경우 스크롤 */
            height: 70%; /* 높이 70%로 조정 */
        }
        #infoMessage {
            background-color: #ffcccb;
            text-align: center;
            padding: 2px;
            height: 10%; /* 높이 10%로 조정 */
        }
        input[type="text"] {
            width: 75%;
            padding: 10px;
            margin-right: 5%;
            box-sizing: border-box; /* 패딩을 너비에 포함 */
        }
        button {
            margin-top: 10px;
            width: 20%;
            padding: 10px;
            background-color: #7971ea; /* 버튼 배경 색상 */
            color: white; /* 버튼 글자 색상 */
            border: none;
            cursor: pointer;
        }
        #review {
            position: fixed; /* 버튼을 고정 위치에 두기 */
            bottom: 25%; /* 화면 하단에서부터의 거리 */
            left: 50%; /* 화면 왼쪽에서 중앙으로 */
            transform: translateX(-50%); /* 버튼의 중앙을 정확히 화면 중앙에 위치시키기 */
            z-index: 10; /* 다른 요소보다 위에 오도록 z-인덱스 설정 */
            padding: 10px 20px; /* 패딩으로 버튼 크기 조정 */
            font-size: 1em; /* 글자 크기 */
            background-color: #4CAF50; /* 버튼 배경 색상 */
            color: white; /* 글자 색상 */
            border: none; /* 테두리 제거 */
            border-radius: 5px; /* 모서리 둥글게 */
            cursor: pointer; /* 마우스 오버 시 커서 변경 */
        }

        button:hover {
            background-color: #45a049; /* 버튼 오버 시 배경 색상 변경 */
        }


        p {
            margin: 0 0 10px 0;
        }
    </style>
    <script type="text/javascript">
        const socket = io('http://localhost:3000/chat');
        let time = new Date();
        let month = time.getMonth();
        let date = time.getDate();
        let hours = time.getHours();
        let minutes = time.getMinutes();
        let now = month + "월" + date + "일" + hours + "시" + minutes + "분";

        let loginId;
        let userId=`${loginId}`
        let currentPageUrl = window.location.href; // 현재 페이지 URL
        let message =null;
        window.onload = function () {
            // URL로부터 쿼리 파라미터 가져오기
            var queryParams = new URLSearchParams(window.location.search);
            let writeDate = queryParams.get('writeDate');
            let boardName = queryParams.get('boardName');
            console.log(boardName + "제목")
            console.log(writeDate + "작성일");
            loginId = queryParams.get('buyerId');
            console.log(loginId + "로그인아이디")
            let boardWriter = queryParams.get('boardWriter');

            if (loginId === boardWriter) {
                alert("본인 게시글입니다.");
                return;
            }

            autoJoinRoomName =  loginId + boardWriter;
            console.log(autoJoinRoomName);

            joinRoom();

            document.getElementById('exitButton').addEventListener('click', function() {
                window.close(); // 창 닫기
            });
        }

        function joinRoom() {
            socket.emit('joinRoom', autoJoinRoomName, {
                UserId: userId
            });
        }

        function sendMessage(event) {
            event.preventDefault();
            time = new Date();

            month = time.getMonth();
            date = time.getDate();
            hours = time.getHours();
            minutes = time.getMinutes();
            now = (month + 1) + "월" + date + "일 " + hours + "시" + minutes + "분";

            const message = document.getElementById('message').value;
            socket.emit('message', autoJoinRoomName, message);
            document.getElementById('message').value = '';
            currentPageUrl = window.location.href; // 현재 페이지 URL
            console.log(currentPageUrl+"url.jsp")

            $.ajax({
                url:"/rest?key=message&methodName=insertMessage",
                type: "post",
                dataType: "json",
                data: { // 올바른 속성 이름으로 수정
                    url: currentPageUrl, // 현재 페이지의 URL을 전송하기 위한 속성
                    userId: '${loginId}',
                    content: message
                },
                success: function (result) {
                    console.log("통신성공");
                },
                error: function (err) {
                    console.log("에러발생");
                }
            });

        }

        socket.on('message', (data) => {
            console.log('Received message:', data.msg);
            const messageArea = document.getElementById('textmessage');
            const msgElement = document.createElement('p');
            const now = new Date();
            const formattedTime = now.getFullYear() + '년 ' + (now.getMonth() + 1) + '월 ' + now.getDate() + '일 ' + now.getHours() + '시 ' + now.getMinutes() + '분 ' + now.getSeconds() + '초';
            msgElement.innerHTML = data.userId + '님: ' + data.msg + '<br>' + formattedTime + '<br>';
            messageArea.prepend(msgElement);
        });
    </script>
</head>
<body>
<div style="background-color: #ffcccb; text-align: center; padding: 10px;">
    2명이 넘어가거나,
    중복된 입장시 채팅이 불가능합니다. 이점 유의해주세요.
</div>

<div id="textmessage" style="margin-top: 20px;"></div>
<form onsubmit="sendMessage(event)">
    <button type="button" id="review" name="review" style="margin-top: 20px;" onclick="location.href='/pages/review/post-detail.jsp'">리뷰하기</button>
    <input type="text" id="message" placeholder="메시지 입력" />
    <button type="submit">전송하기</button>
    <button id="exitButton">나가기</button>
</form>
</body>
</html>
