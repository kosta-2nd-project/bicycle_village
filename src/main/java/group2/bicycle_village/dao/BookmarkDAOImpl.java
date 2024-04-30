package group2.bicycle_village.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group2.bicycle_village.common.dto.BookmarkEntity;
import group2.bicycle_village.common.dto.BookmarkListDTO;
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
		String sql = "delete bookmark where board_seq=? and user_seq=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, (int)bookmark.getBoardSeq());
			ps.setInt(2, (int)bookmark.getUserSeq());
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	@Override
	public List<BookmarkListDTO> selectAll(int userSeq) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select b.board_seq, b.board_name, m.nickname, b.reg_date, b.goods_price, b.board_addr from board b join member m using(user_seq) where b.board_seq in (select board_seq from bookmark where user_seq=?)";
		List<BookmarkListDTO> list = new ArrayList<BookmarkListDTO>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, userSeq);
			rs = ps.executeQuery();
			while(rs.next()) {
				BookmarkListDTO bookmark = new BookmarkListDTO(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6));
				list.add(bookmark);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

}
