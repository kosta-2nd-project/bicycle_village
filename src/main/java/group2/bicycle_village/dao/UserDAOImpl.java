package group2.bicycle_village.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import group2.bicycle_village.common.constant.CommonCode;
import group2.bicycle_village.common.dto.BoardEntity;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.common.util.DbUtil;

public class UserDAOImpl implements UserDAO {
	public UserDAOImpl() {

	}

	@Override
	public UserDTO loginCheck(UserDTO userDTO) throws SQLException {
		//BoardEntity entity = new BoardEntity.Builder().addr("").category(CommonCode.BoardCategory.getStatus(0)).build();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		UserDTO dbDTO =null;
		
		String sql= "select user_seq,user_id,user_pwd,user_name,nickname,user_tel,birth,user_email,user_gender,status"
				+ " from member where user_id=? and user_pwd=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userDTO.getUserId());
			ps.setString(2, userDTO.getPwd());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				dbDTO = new UserDTO(rs.getInt(1),rs.getString(2), rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10));
			}
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return dbDTO;
	}

	@Override
	public int insert(UserDTO userDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "insert into member(user_id,user_pwd,user_name,nickname,user_tel,birth,user_email,user_gender,status,reg_date,edit_date)"
				+ " values(?,?,?,?,?,?,?,?,?,sysdate,null)";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userDTO.getUserId());
			ps.setString(2, userDTO.getPwd());
			ps.setString(3, userDTO.getName());
			ps.setString(4, userDTO.getNickName());
			ps.setString(5, userDTO.getTel());
			ps.setString(6, userDTO.getBirth());
			ps.setString(7, userDTO.getEmail());
			ps.setString(8, userDTO.getGender());
			ps.setInt(9,userDTO.getStatus());
			
			result = ps.executeUpdate();
			
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	@Override
	public UserDTO selectById(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select user_seq, user_id, user_pwd, user_name, nickname, user_tel, birth, user_email, user_gender,status from member where user_id=?";
		UserDTO user = null;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1,id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new UserDTO(rs.getInt(1),rs.getString(2), rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10));
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return user;
	}

	@Override
	public int updateInfo(UserDTO userDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update member set user_pwd=?,nickname=?,user_tel=?,user_email=?,user_gender=? where user_id=?";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userDTO.getPwd());
			ps.setString(2, userDTO.getNickName());
			ps.setString(3, userDTO.getTel());
			ps.setString(4, userDTO.getEmail());
			ps.setString(5, userDTO.getGender());
			ps.setString(6, userDTO.getUserId());
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	@Override
	public int delAccount(UserDTO userDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql= "update member set status=2, edit_date=sysdate where user_id=? and user_pwd=?";
		int result;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			System.out.println(userDTO.getUserId());
			ps.setString(1, userDTO.getUserId());
			ps.setString(2, userDTO.getPwd());
			result = ps.executeUpdate();
			System.out.println("result 완료");
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	@Override
	public int changeStatus(UserDTO userDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update member set status=0 where user_id=? and user_pwd=?";
		int result;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userDTO.getUserId());
			ps.setString(2, userDTO.getPwd());
			result = ps.executeUpdate();
			
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}
}
