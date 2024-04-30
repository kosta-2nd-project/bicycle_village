package group2.bicycle_village.dao;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BookmarkEntity;
import group2.bicycle_village.common.dto.BookmarkListDTO;

public interface BookmarkDAO {
	/**
	 * 찜 추가 기능
	 */
	int addBookmark(BookmarkEntity bookmark) throws SQLException;
	
	/**
	 * 찜 삭제 기능
	 */
	int delBookmark(BookmarkEntity bookmark) throws SQLException;
	
	/**
	 * 전체 조회
	 */
	List<BookmarkListDTO> selectAll(int userSeq) throws SQLException;
}
