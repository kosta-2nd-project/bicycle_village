const express = require('express');
const socketIo = require('socket.io');
const app = express();
const port = 3000;
const cors = require('cors');
let realId='';
let sellerId='';
let duplicater=null;
const server = app.listen( port, () => {
    console.log('Express listening on port', port);
});

app.use(express.json());
app.use(cors());
//소켓서버를 웹서버에 붙인다.
const io = socketIo(server, { path: '/socket.io' });

const namespaces = {};

app.post('/', function(req,res){
    const content = req.body.content;
    const userId = req.body.userId;
    const boardSeq = req.body.boardSeq;
    const SellerId = req.body.sellerId;
    console.log("컨텐트 내용"+content)
    console.log("로그인네임" + userId);
    console.log("보드 시퀀스"+boardSeq);
    console.log("셀러 아이디"+SellerId);
    realId = userId;
    sellerId= SellerId;
    duplicater= content;
})


app.set('io', io);
app.get('/', (req, res) => {



    if (!namespaces[sellerId]) {
            console.log(sellerId+"셀러아이디get");
        const roomOwner= io.of('/'+sellerId);
        roomOwner.on('connection', function (socket){
            duplicater=socket.content;
            console.log(duplicater);

            socket.username = realId; // 사용자 이름 설정
            socket.sellerId=sellerId;
            console.log(socket.sellerId+"의 스페이스에 접속.");

            socket.broadcast.emit('join', {username: socket.username});

            socket.on('duplicate Check', function (data){
                roomOwner.emit('server message',{
                    message : duplicater
                })
            })



            socket.on('client message', function (data){
                roomOwner.emit('server message',{
                    username: socket.username,
                    message: data.message
                })
            })

            socket.on('disconnect',function (){
                console.log(socket.username + "이(가) 방을 떠났습니다.");
                socket.broadcast.emit('leave', { username: socket.username }); // 모든 클라이언트에게 사용자의 퇴장을 알립니다.
                socket.leave(realId); // 방에서 사용자를 제거합니다
            })
        })
        namespaces[sellerId] = true;
    }

    res.sendFile(__dirname + '/chatting/room.html');

});

//네임 스페이스 등록 - userId로 생성  ( 아이디에 해당하는 채팅 리스트 )
const room = '';

