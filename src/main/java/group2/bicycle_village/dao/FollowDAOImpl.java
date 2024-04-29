package group2.bicycle_village.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group2.bicycle_village.common.dto.FollowEntity;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.common.util.DbUtil;

public class FollowDAOImpl implements FollowDAO {

	@Override
	public int addFollow(FollowEntity follow) throws SQLException {
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
	public int delFollow(FollowEntity follow) throws SQLException {
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

	@Override
	public List<FollowEntity> selectAllFollow(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="select * from follow where follower = (select user_seq from member where user_id = ?)";
		List<FollowEntity> list = new ArrayList<FollowEntity>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new FollowEntity.Builder().followSeq(rs.getInt(1)).follow(rs.getInt(2)).follower(rs.getInt(3)).build());
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<UserDTO> searchNicknameUserId(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="select user_id, nickname from member where user_seq in " +
				"(select follow from follow where follower = " +
				"(select user_seq from member where user_id = ?))";
		List<UserDTO> list = new ArrayList<>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				UserDTO user = new UserDTO(rs.getString(1),null, null, rs.getString(2),null,null,null,null,0);
				list.add(user);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}
}
