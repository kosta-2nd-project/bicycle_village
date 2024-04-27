package group2.bicycle_village.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import group2.bicycle_village.common.dto.BookmarkEntity;
import group2.bicycle_village.common.util.DbUtil;

public class BookmarkDAOImpl implements BookmarkDAO {

	@Override
	public int addBookmark(BookmarkEntity bookmark) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "insert into bookmark(user_seq,board_seq) values(?,?)";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, (int)bookmark.getUserSeq());
			ps.setInt(2, (int)bookmark.getBoardSeq());
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	@Override
	public int delBookmark(BookmarkEntity bookmark) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete bookmark where board_seq=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, (int)bookmark.getBoardSeq());
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

}
