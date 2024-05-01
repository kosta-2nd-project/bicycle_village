package group2.bicycle_village.service;

import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.dao.AlarmDAO;
import group2.bicycle_village.dao.AlarmDAOImpl;
import group2.bicycle_village.dao.BoardDAOImpl;
import group2.bicycle_village.dao.BoardDao;
import group2.bicycle_village.exception.AuthenticationException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlarmServiceImpl implements AlarmService {
    private AlarmDAO alarmDAO = new AlarmDAOImpl();
    private BoardDTO board = new BoardDTO();
//    @Override
//    public int insertFollow(AlarmDTO alarm) throws SQLException {
//        int result = 0;
//        //board가 insert 됐을 때 - 해당 board에서 userSeq 가져오기
//        UserDTO userDTO = alarmDAO.userIdAndNickname(board.getUserSeq());
//        List<UserDTO> follower = alarmDAO.searchFollower(board.getUserSeq());
//        for (UserDTO user : follower) {
//           result += alarmDAO.insertAlarm(new AlarmDTO(user.getUser_seq(),userDTO.getNickName()+"("+userDTO.getUserId()+")님이 게시물을 작성했습니다.",0));
//        }
//        return result;
//    }

//    @Override
//    public int insertDips(AlarmDTO alarm) throws SQLException {
//        int result = 0;
//        //board가 update 됐을 때 - 해당 board에서 boardSeq 가져오기
//        List<UserDTO> Dips = alarmDAO.searchFollower(board.getBoardSeq());
//        for (UserDTO user : Dips) {
//            result += alarmDAO.insertAlarm(new AlarmDTO(user.getUser_seq(),"찜한 게시물이 수정되었습니다.",0));
//        }
//        result = alarmDAO.insertAlarm(alarm);
//        return result;
//    }

    @Override
    public List<AlarmDTO> selectAllAlarm(String id) throws SQLException {
        return alarmDAO.selectAllAlarm(id);
    }

    @Override
    public int updateAlarm(String id, long alarmSeq) throws SQLException {
        return alarmDAO.updateAlarm(id, alarmSeq);
    }

    @Override
    public String linked(long alarmSeq) throws SQLException {
        return alarmDAO.linked(alarmSeq);
    }

    @Override
    public String linkURL(long boardSeq) throws SQLException {
        String url = "front?key=board&methodName=selectByBoardSeq&boardSeq=" + boardSeq;
        return url;
    }

    @Override
    public long searchBoardSeq(long userSeq) throws SQLException {
        return alarmDAO.searchBoardSeq(userSeq);
    }

    @Override
    public int setLinkURL(String url) throws SQLException {
        return alarmDAO.setLinkURL(url);
    }

    @Override
    public int alarmCheck(String id) throws SQLException, AuthenticationException {
        System.out.println("AlarmService id: "+id);
        List<Integer> list = alarmDAO.alarmCheck(id);
        List<Integer> nlist = new ArrayList<>();
        System.out.println("AlarmServiceImpl list: "+list);
        int num = 0;
        if(list == null) {
            throw new AuthenticationException("알림 갯수 조회 실패");
        }
        for (int i : list) {
            if(i == 0) {
                num += i;
                nlist.add(num);
            }
        }
        int result = nlist.size();
        System.out.println("AlarmService list length: "+result);
        return result;
    }
}
