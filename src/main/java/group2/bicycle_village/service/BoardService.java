package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.BoardEntity;

public interface BoardService {
	
	void insert(BoardEntity board, String url) throws SQLException;

	void delete(int boardSeq) throws SQLException;


	void update(BoardEntity board) throws SQLException;


	List<BoardDTO> selectAll() throws SQLException;
	
	List<BoardDTO> selectAll(int pageNo) throws SQLException;
	
	BoardDTO selectByBoardSeq(int boardSeq, boolean flag) throws SQLException;

	/**
	 * insert된 board의 URL 주소 찾기
	 */
//	String linkURL(long boardSeq) throws SQLException;

	/**
	 * URL을 찾을 board의 seq 찾기
	 */
//	long searchBoardSeq(long userSeq) throws SQLException;
}
