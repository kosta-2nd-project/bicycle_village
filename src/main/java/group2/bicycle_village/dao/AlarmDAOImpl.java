package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.AlarmDTO;
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
    public int insertAlarm(AlarmDTO alarm) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "insert into alarm values(alarm_seq.nextval, ?, ?, ?, null)";
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, alarm.getUserSeq());
            ps.setString(2, alarm.getAlarmContent());
            ps.setInt(3,alarm.getIsSeen());
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
        String sql = "SELECT alarm_content FROM alarm WHERE user_seq in (SELECT user_seq FROM member WHERE user_id = ?)";
        List<AlarmDTO> alarmList = new ArrayList<AlarmDTO>();
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                //알림 내용 읽어오기
                AlarmDTO alarm = new AlarmDTO(rs.getString("alarm_content"), 0);
                alarmList.add(alarm);
            }
        } finally {
            DbUtil.close(con, ps, rs);
        }
        return alarmList;
    }

    @Override
    public int updateAlarm(AlarmDTO alarm) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "";
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
//            ps.setLong();
            result = ps.executeUpdate();
        } finally {
            DbUtil.close(con, ps, null);
        }
        return result;
    }

    @Override
    public List<String> linked(String linkURL) throws SQLException {
        return List.of();
    }

    @Override
    public UserDTO userIdAndNickname(long userSeq) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select distinct m.nickname, m.user_id from board b join member m using(user_seq) where user_seq in (select user_seq from member where usesr_id = ?)";
        UserDTO user = null;
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, userSeq);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new UserDTO(rs.getString(2), null, null, rs.getString(1), null, null, null, null, 0);
            }
        } finally {
            DbUtil.close(con, ps, rs);
        }
        return user;
    }

    @Override
    public List<UserDTO> searchFollower(long userSeq) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from member where user_seq in (select follower from follow where follow = ?)";
        List<UserDTO> userList = new ArrayList<>();
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, userSeq);
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
    public List<UserDTO> searchDips(long userSeq) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from member where user_seq in (select user_seq from Dips where user_seq = ?)";
        List<UserDTO> userList = new ArrayList<>();
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, userSeq);
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
}
