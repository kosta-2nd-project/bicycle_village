package group2.bicycle_village.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import group2.bicycle_village.common.dto.followEntity;
import group2.bicycle_village.common.util.DbUtil;

public class FollowDAOImpl implements FollowDAO {

	@Override
	public int addFollow(followEntity follow) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql="insert into follow(follow,follower) values(?,?)";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, (int)follow.getFollow());
			ps.setInt(2, (int)follow.getFollower());
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);
			
		}
		return result;
	}

	@Override
	public int delFollow(followEntity follow) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete follow where follow_seq=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, (int)follow.getFollowSeq());
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

}
