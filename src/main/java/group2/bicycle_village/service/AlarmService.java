package group2.bicycle_village.service;

import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.dao.AlarmDAO;
import group2.bicycle_village.exception.AuthenticationException;

import java.sql.SQLException;
import java.util.List;

public interface AlarmService {
//    int insertFollow(AlarmDTO alarm) throws SQLException;

//    int insertDips(AlarmDTO alarm) throws SQLException;

    int insertAlarm(long userSeq) throws SQLException;
    List<AlarmDTO> selectAllAlarm(String id) throws SQLException;

    int updateAlarm(String id, long alarmSeq) throws SQLException;

    String linked(long alarm) throws SQLException;

    /**
     * insert된 board의 URL 주소 찾기
     * @param boardSeq
     * @return
     * @throws SQLException
     */
    String linkURL(long boardSeq, String methodName) throws SQLException;

    long searchBoardSeq(long userSeq) throws SQLException;

    int setLinkURL(String url) throws SQLException;

    /**
     * 확인하지 않은 알림의 갯수
     * @param id
     * @return
     * @throws SQLException
     */
    int alarmCheck(String id) throws SQLException, AuthenticationException;
}
