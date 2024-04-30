package group2.bicycle_village.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.util.DbUtil;

public class IndexPostDAOImpl implements IndexPostDAO {

	@Override
	public List<BoardDTO> selectBest() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql="SELECT board_seq, board_name, reg_date, category, board_content, board_addr, board_count, goods_price, user_seq\r\n"
				+ "FROM(SELECT *\r\n"
				+ "FROM board\r\n"
				+ "ORDER BY reg_date DESC)\r\n"
				+ "WHERE rownum<=5";
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO(rs.getInt(1), null, rs.getInt("board_count"), rs.getInt("goods_price"),
						0, rs.getInt("user_seq"), rs.getString("board_name"), rs.getString("reg_date"),
						rs.getString("category"), 0, rs.getString("board_content"), null);
				list.add(board);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		
		return list;
	}

}
