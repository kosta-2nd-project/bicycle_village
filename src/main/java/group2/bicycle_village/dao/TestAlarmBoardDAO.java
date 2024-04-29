package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.BoardEntity;

import java.sql.SQLException;

public interface TestAlarmBoardDAO {
    int insert(BoardEntity board) throws SQLException;
    int update(BoardEntity board) throws SQLException;
    /**
     * 로그인한 유저의 가장 최근 게시물 찾기
     * @param userSeq
     * @return
     * @throws SQLException
     */
    long serachBoardSeq(long userSeq) throws SQLException;
}
