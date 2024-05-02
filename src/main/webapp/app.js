const express = require('express');
const app = express(); // express 애플리케이션 인스턴스 생성
const http = require('http').createServer(app); // express 인스턴스를 http 서버에 연결
const io = require('socket.io')(http, {
    cors: {
        origin: "http://localhost:9000", // 클라이언트의 origin을 허용
        methods: ["GET", "POST"] // 허용할 HTTP 메서드
    }
});
const port = 3000;

app.post('/login', function(req, res){
    const loginId = req.body.loginId;
    console.log("로그인 ID: " + loginId);
    // 로그인 ID에 대한 처리 로직을 여기에 작성
    res.status(200).send(`로그인 ID ${loginId}를 받았습니다.`);
});



const chatNamespace = io.of('/chat');

chatNamespace.on('connection', (socket) => {
    console.log(socket.userId + '유저가 채팅 네임스페이스에 연결되었습니다.');

    socket.on('joinRoom', async (room, userData) => {
        const userId = userData.UserId;
        socket.userId = userId; // 사용자 ID를 소켓 객체에 추가합니다.

        // 룸에 있는 소켓 ID들을 가져옵니다.
        const socketsInRoom = await chatNamespace.in(room).allSockets();
        const numberOfUsersInRoom = socketsInRoom.size;

        // 같은 userId를 가진 사용자가 룸에 있는지 확인합니다.
        let isDuplicateUser = false;
        socketsInRoom.forEach(socketId => {
            if (chatNamespace.sockets.get(socketId).userId === userId) {
                isDuplicateUser = true;
            }
        });

        // 인원수가 2명이 넘거나 같은 userId를 가진 사용자가 있는 경우
        if (numberOfUsersInRoom >= 2 || isDuplicateUser) {
            // 해당 소켓에게만 에러 메시지를 전송한 뒤 연결을 끊습니다.
            socket.emit('error', '방 인원이 2명을 초과했거나 이미 같은 아이디가 방에 있습니다. 입장이 불가능합니다.');
            socket.disconnect();
        } else {
            // 조건을 만족하면 룸에 참여합니다.
            socket.join(room);
            console.log(`유저 ${userId}가 방 ${room}에 참여했습니다.`);
        }
    });

    socket.on('message', (room, msg) => {
        const data = {
            msg,
            userId: socket.userId,
        };
        chatNamespace.to(room).emit('message', data);
    });

    socket.on('disconnect', () => {
        console.log(`${socket.userId} 유저가 채팅 네임스페이스에서 연결을 끊었습니다.`);
    });
});

http.listen(port, () => {
    console.log(`채팅 서버가 ${port}번 포트에서 실행 중입니다.`);
});
