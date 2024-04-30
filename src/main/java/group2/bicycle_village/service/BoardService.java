package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.BoardEntity;
import group2.bicycle_village.common.dto.CommentEntity;
import group2.bicycle_village.common.dto.CommentsDTO;

public interface BoardService {
	

	int insert(BoardEntity board) throws SQLException;


	void delete(long boardSeq) throws SQLException;


	void update(BoardEntity board) throws SQLException;


	List<BoardDTO> selectAll() throws SQLException;
	
	List<BoardDTO> selectAll(int pageNo) throws SQLException;
	
	BoardDTO selectByBoardSeq(long boardSeq, boolean flag) throws SQLException;

	List<BoardDTO> selectByCateory(int category) throws SQLException;

	List<BoardDTO> getBoardListByCateory(int category, int pageNo) throws SQLException;

	long searchBoardSeq(long userSeq) throws SQLException;
	
	List<CommentsDTO> getComment(long boardSeq) throws SQLException;

	int insertComment(CommentEntity comment) throws SQLException;


	/**
	 *  댓글 삭제
	 * */
	int deleteComment(long boardSeq) throws SQLException;


	/**
	 *  댓글 수정
	 * */
	int updateComment(CommentEntity comment) throws SQLException;
}
