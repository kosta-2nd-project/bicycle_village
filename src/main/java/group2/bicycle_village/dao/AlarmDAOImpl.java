package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.common.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlarmDAOImpl implements AlarmDAO {
    @Override
    public int insertAlarm(String id, AlarmDTO alarm) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "insert into alarm values(alarm_seq.nextval, (select user_seq from member where user_id = ?), ?, ?, ?)";
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, alarm.getAlarmContent());
            ps.setInt(3,alarm.getIsSeen());
            ps.setString(4, alarm.getLinkURL());
            result = ps.executeUpdate();
        } finally {
            DbUtil.close(con, ps, null);
        }

        return result;
    }

    @Override
    public List<AlarmDTO> selectAllAlarm(String id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        List<AlarmDTO> alarmList = new ArrayList<AlarmDTO>();

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                //알림 내용 읽어오기
                AlarmDTO alarm = new AlarmDTO();
            }
        } finally {
            DbUtil.close(con, ps, rs);
        }

        return alarmList;
    }

    @Override
    public int updateAlarm(String id, AlarmDTO alarm) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "";

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            result = ps.executeUpdate();
        } finally {
            DbUtil.close(con, ps, null);
        }
        return 0;
    }

    @Override
    public List<String> linked(String linkURL) throws SQLException {
        return List.of();
    }

    @Override
    public List<UserDTO> searchFollower(String id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from member where user_seq in (select follower from follow where follow in (select user_seq from member where user_id = ?))";
        List<UserDTO> userList = new ArrayList<>();
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO(rs.getInt("user_seq"), rs.getString("user_id"),
                        rs.getString("user_pwd"), rs.getString("user_name"),
                        rs.getString("nickname"), rs.getString("user_tel"),
                        rs.getString("birth"), rs.getString("user_email"),
                        rs.getString("user_gender"), rs.getInt("status"),
                        rs.getDate("reg_date"));
                userList.add(user);
            }
        } finally {
            DbUtil.close(con, ps, rs);
        }
        return userList;
    }

    public List<UserDTO> searchDips(String id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from member where user_seq in (select follower from follow where follow in (select user_seq from member where user_id = ?))";
        List<UserDTO> userList = new ArrayList<>();
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO(rs.getInt("user_seq"), rs.getString("user_id"),
                        rs.getString("user_pwd"), rs.getString("user_name"),
                        rs.getString("nickname"), rs.getString("user_tel"),
                        rs.getString("birth"), rs.getString("user_email"),
                        rs.getString("user_gender"), rs.getInt("status"),
                        rs.getDate("reg_date"));
                userList.add(user);
            }
        } finally {
            DbUtil.close(con, ps, rs);
        }
        return userList;
    }

    @Override
    public BoardDTO followBoardContent(String id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        BoardDTO board = null;
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                board = new BoardDTO();
            }
        } finally {
            DbUtil.close(con, ps, rs);
        }
        return board;
    }
}
