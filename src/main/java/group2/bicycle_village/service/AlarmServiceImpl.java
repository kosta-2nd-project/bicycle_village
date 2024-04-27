package group2.bicycle_village.service;

import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.dao.AlarmDAO;
import group2.bicycle_village.dao.AlarmDAOImpl;
import group2.bicycle_village.dao.BoardDAOImpl;
import group2.bicycle_village.dao.BoardDao;

import java.sql.SQLException;
import java.util.List;

public class AlarmServiceImpl implements AlarmService {
    private AlarmDAO alarmDAO = new AlarmDAOImpl();
    private BoardDTO board = new BoardDTO();
    @Override
    public int insertFollow(AlarmDTO alarm) throws SQLException {
        int result = 0;
        //board가 insert 됐을 때
        UserDTO userDTO = alarmDAO.userIdAndNickname(board.getUserSeq());
        List<UserDTO> follower = alarmDAO.searchFollower(board.getUserSeq());
        for (UserDTO user : follower) {
           result += alarmDAO.insertAlarm(new AlarmDTO(user.getUser_seq(),userDTO.getNickName()+"("+userDTO.getUserId()+")님이 게시물을 작성했습니다.",0));
        }
        return result;
    }

    @Override
    public int insertDips(AlarmDTO alarm) throws SQLException {
        int result = 0;
        //board가 update 됐을 때
        List<UserDTO> Dips = alarmDAO.searchFollower(board.getUserSeq());
        for (UserDTO user : Dips) {
            result += alarmDAO.insertAlarm(new AlarmDTO(user.getUser_seq(),"찜한 게시물이 수정되었습니다.",0));
        }
        result = alarmDAO.insertAlarm(alarm);
        return result;
    }

    @Override
    public List<AlarmDTO> selectAllAlarm(String id) throws SQLException {
        return alarmDAO.selectAllAlarm(id);
    }

    @Override
    public int updateAlarm(AlarmDTO alarm) throws SQLException {
        return alarmDAO.updateAlarm(alarm);
    }

    @Override
    public List<String> linked(String linkURL) throws SQLException {
        return List.of();
    }
}
