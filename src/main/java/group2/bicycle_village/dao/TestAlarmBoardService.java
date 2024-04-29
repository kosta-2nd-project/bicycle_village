package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.BoardEntity;

import java.sql.SQLException;

public interface TestAlarmBoardService {
    void insert(BoardEntity board, String url) throws SQLException;
    void update(BoardEntity board, String url) throws SQLException;
    /**
     * insert된 board의 URL 주소 찾기
     */
    String linkURL(long boardSeq) throws SQLException;

    /**
     * URL을 찾을 board의 seq 찾기
     */
    long searchBoardSeq(long userSeq) throws SQLException;
}
