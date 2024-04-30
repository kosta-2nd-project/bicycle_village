package group2.bicycle_village.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group2.bicycle_village.common.dto.PostDTO;
import group2.bicycle_village.common.util.DbUtil;

public class PostListDAOImpl implements PostListDAO {

	@Override
	public List<PostDTO> selectAll(int userSeq) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql ="SELECT b.board_seq, b.board_name, b.category, b.reg_date, b.board_count, COUNT(c.comment_seq) as comment_cnt, f.image_name, f.save_number\r\n"
				+ "FROM boardfile f RIGHT JOIN board b on b.board_seq = f.board_seq \r\n"
				+ "LEFT JOIN comments c ON b.board_seq = c.board_seq\r\n"
				+ "WHERE b.user_seq = ? and f.save_number = 0 or f.save_number is null\r\n"
				+ "GROUP BY b.board_seq, b.category, b.reg_date, b.board_count, b.board_name, f.image_name, f.save_number\r\n"
				+ "ORDER BY reg_date desc";
		
		List<PostDTO> list = new ArrayList<PostDTO>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, userSeq);
			rs = ps.executeQuery();
			while(rs.next()) {
				PostDTO post = new PostDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), 
						rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8));
				list.add(post);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	@Override
	public int delPost(PostDTO postDto) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete board where board_seq=? and user_seq=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, (int)postDto.getBoardSeq());
			ps.setInt(2, (int)postDto.getUserSeq());
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

}
