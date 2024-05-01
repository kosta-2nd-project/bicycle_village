package group2.bicycle_village.alarmTest;

import group2.bicycle_village.common.dto.BoardEntity;

import java.sql.SQLException;

public class TestAlarmBoardServiceImpl implements TestAlarmBoardService {
    @Override
    public void insert(BoardEntity board, String url) throws SQLException {
//        int result = boardDAO.insert(board);
//		long boardSeq = boardDAO.serachBoardSeq(board.getUserSeq());
//        if(result==0) throw new SQLException("등록되지 않았습니다");
//        else {
//            UserDTO userDTO = alarmDAO.userIdAndNickname(board.getUserSeq());
//            List<UserDTO> follower = alarmDAO.searchFollower(board.getUserSeq());
//            int re = 0;
//            for (UserDTO user : follower) {
//                re += alarmDAO.insertAlarm(new AlarmDTO(user.getUser_seq(), userDTO.getNickName()+"("+userDTO.getUserId()+")님이 게시물을 작성했습니다.",0, url));
//            }
//        }
    }

    @Override

    public void update(BoardEntity board/*, String url*/) throws SQLException {
//        int result = boardDAO.update(board);
//        if(result==0) throw new SQLException("수정되지 않았습니다");
//        else {
//            List<UserDTO> dips = alarmDAO.searchDips(board.getBoardSeq());
//            System.out.println("BoardServiceImpl update");
//            int re = 0;
//            for (UserDTO user : dips) {
//                re += alarmDAO.insertAlarm(new AlarmDTO(user.getUser_seq(), "찜한 게시물이 수정되었습니다.",0, url));
//            }
//        }
    }

    @Override
    public String linkURL(long boardSeq) throws SQLException {
        String url = "front?key=board&methodName=selectByBoardSeq&boardSeq=" + boardSeq;
        return url;
    }

    @Override
    public long searchBoardSeq(long userSeq) throws SQLException {
        return new TestAlarmBoardDAOImpl().serachBoardSeq(userSeq);
    }
}
