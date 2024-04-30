package group2.bicycle_village.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.BoardEntity;
import group2.bicycle_village.common.dto.CommentEntity;
import group2.bicycle_village.common.dto.CommentsDTO;

public interface BoardDao {
	
	public void test();

	int insert(BoardEntity board) throws SQLException;

	int delete(long boardSeq) throws SQLException;

	int update(BoardEntity board) throws SQLException;

	BoardDTO selectByBoardSeq(long boardSeq) throws SQLException;

	List<BoardDTO> selectAll() throws SQLException;

	List<BoardDTO> getBoardList(int pageNo) throws SQLException;

	int increamentByReadnum(long board_seq) throws SQLException;

	List<BoardDTO> selectByCateory(int category) throws SQLException;

	List<BoardDTO> getBoardListByCateory(int category, int pageNo) throws SQLException;

	long searchBoardSeq(long userSeq) throws SQLException;

	/**
	 * 댓글정보 가져오기 
	 * */
	List<CommentsDTO> getComment(long boardSeq) throws SQLException;

	/**
	 * 댓글정보 입력
	 * */
	int insertComment(CommentEntity comment) throws SQLException;

	/**
	 * 댓글 삭제 (is_seen 상태 변경)
	 * */
	int deleteComment(long commentSeq) throws SQLException;

	/**
	 * 대댓글정보 가져오기
	 * */
	List<CommentsDTO> getReComment(CommentsDTO comment) throws SQLException;

	/**
	 * 댓글 수정
	 * */
	int updateComment(CommentEntity comment) throws SQLException;
	
}
