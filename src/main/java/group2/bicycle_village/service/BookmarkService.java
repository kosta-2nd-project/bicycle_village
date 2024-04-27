package group2.bicycle_village.service;

import java.sql.SQLException;

import group2.bicycle_village.common.dto.BookmarkEntity;
import group2.bicycle_village.exception.AuthenticationException;

public interface BookmarkService {

	/**
	 * 찜 추가
	 */
	void AddBookmark(BookmarkEntity bookmark) throws SQLException, AuthenticationException;
	
	/**
	 * 찜 삭제
	 */
	void DeleteBookmark(BookmarkEntity bookmark) throws SQLException, AuthenticationException;
}
