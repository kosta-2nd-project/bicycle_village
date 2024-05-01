package group2.bicycle_village.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.PostDTO;
import group2.bicycle_village.common.util.DbUtil;

public class IndexPostDAOImpl implements IndexPostDAO {

	@Override
	public List<PostDTO> selectBest() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql="SELECT board_seq, board_name, goods_price, image_name, save_number, reg_date\r\n"
				+ "FROM (\r\n"
				+ "SELECT board_seq, board_name, goods_price, image_name, save_number, reg_date\r\n"
				+ "FROM board LEFT JOIN boardfile using(board_seq)\r\n"
				+ "WHERE category = 3 and (save_number is null or save_number = 0)\r\n"
				+ "ORDER BY reg_date DESC )\r\n"
				+ "WHERE rownum <= 5";
		List<PostDTO> list = new ArrayList<PostDTO>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				PostDTO post = new PostDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
				list.add(post);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		
		return list;
	}

}
