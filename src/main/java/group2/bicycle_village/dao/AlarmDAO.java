package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.AlarmDTO;

import java.sql.SQLException;
import java.util.List;

public interface AlarmDAO {

    /**
     * 팔로우한 사용자가 게시물을 올렸을 때 알림테이블에 알림 추가
     * @param id
     * @param alarm
     * @return
     * @throws SQLException
     */
    int insertFollow(String id, AlarmDTO alarm) throws SQLException;

    /**
     * 찜한 게시물이 수정되었을 때 알림테이블에 알림 추가
     * @param id
     * @param alarm
     * @return
     * @throws SQLException
     */
    int insertDips(String id, AlarmDTO alarm) throws SQLException;

    /**
     * 로그인한 유저에게 띄울 알림 목록 조회
     * @param id
     * @return
     * @throws SQLException
     */
    List<AlarmDTO> selectAlarm(String id) throws SQLException;

    /**
     * 유저가 알림을 확인했을 때 알림 상태를 변경 (알림 확인 안했을 때 : 0, 알림 확인 했을 때 : 1)
     * @param id
     * @param alarm
     * @return
     * @throws SQLException
     */
    int updateAlarm(String id, AlarmDTO alarm) throws SQLException;
}
