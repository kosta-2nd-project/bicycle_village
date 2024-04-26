package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.BoardEntity;

public interface BoardService {
	
	void insert(BoardEntity board) throws SQLException;

	void delete(int boardSeq) throws SQLException;

	void update(BoardDTO board) throws SQLException;

	List<BoardDTO> selectAll() throws SQLException;
	
	List<BoardDTO> selectAll(int pageNo) throws SQLException;
	
	BoardDTO selectByBoardSeq(int boardSeq, boolean flag) throws SQLException;


}
