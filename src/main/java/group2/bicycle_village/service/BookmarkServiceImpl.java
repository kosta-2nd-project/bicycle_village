package group2.bicycle_village.service;

import java.sql.SQLException;

import group2.bicycle_village.common.dto.BookmarkEntity;
import group2.bicycle_village.dao.BookmarkDAO;
import group2.bicycle_village.dao.BookmarkDAOImpl;
import group2.bicycle_village.exception.AuthenticationException;

public class BookmarkServiceImpl implements BookmarkService {
	
	BookmarkDAO bookmarkDao = new BookmarkDAOImpl();

	@Override
	public void AddBookmark(BookmarkEntity bookmark) throws SQLException, AuthenticationException {
		int result = bookmarkDao.addBookmark(bookmark);
		if(result==0) {
			throw new AuthenticationException("찜 추가 실패");
		}
	}

	@Override
	public void DeleteBookmark(BookmarkEntity bookmark) throws SQLException, AuthenticationException {
		int result = bookmarkDao.delBookmark(bookmark);
		if(result==0) {
			throw new AuthenticationException("찜 삭제 실패");
		}
	}

}