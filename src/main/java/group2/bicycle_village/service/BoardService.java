package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.BoardEntity;

public interface BoardService {
	
	int insert(BoardEntity board) throws SQLException;

	void delete(int boardSeq) throws SQLException;

	void update(BoardEntity board) throws SQLException;

	List<BoardDTO> selectAll() throws SQLException;
	
	List<BoardDTO> selectAll(int pageNo) throws SQLException;
	
	BoardDTO selectByBoardSeq(int boardSeq, boolean flag) throws SQLException;

	List<BoardDTO> selectByCateory(int category) throws SQLException;

	List<BoardDTO> getBoardListByCateory(int category, int pageNo) throws SQLException;

	long searchBoardSeq(long userSeq) throws SQLException;
}
