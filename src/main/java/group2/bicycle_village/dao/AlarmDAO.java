package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.common.dto.UserDTO;

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
     * 로그인한 유저에게 알림 띄우기 (게시글 제목, 내용, 작성자)
     * @param id
     * @return
     * @throws SQLException
     */
    List<AlarmDTO> selectAllAlarm(String id) throws SQLException;

    /**
     * 유저가 알림을 확인했을 때 알림상태를 변경 (알림 확인 안했을 때 : 0, 알림 확인 했을 때 : 1)
     * @param id
     * @param alarm
     * @return
     * @throws SQLException
     */
    int updateAlarm(String id, AlarmDTO alarm) throws SQLException;

    /**
     * 알림을 클릭하면 해당 알림에 저장된 URL로 페이지 이동
     * @param linkURL
     * @return
     * @throws SQLException
     */
    List<String> linked(String linkURL) throws SQLException;

    /**
     * 로그인된 유저를 팔로우하는 유저들을 찾기
     * @param id
     * @return
     * @throws SQLException
     */
    List<UserDTO> searchFollower(String id) throws SQLException;
}
