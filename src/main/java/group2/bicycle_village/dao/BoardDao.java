package group2.bicycle_village.dao;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.BoardEntity;

public interface BoardDao {
	
	public void test();

	int insert(BoardEntity board) throws SQLException;

	int delete(int boardSeq) throws SQLException;

	int update(BoardDTO board) throws SQLException;

	BoardDTO selectByBoardSeq(int boardSeq) throws SQLException;

	List<BoardDTO> selectAll() throws SQLException;

	List<BoardDTO> getBoardList(int pageNo) throws SQLException;

	int increamentByReadnum(int board_seq) throws SQLException;

	
}
