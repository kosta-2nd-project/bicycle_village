package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.common.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AlarmDAOImpl implements AlarmDAO {
    @Override
    public int insertFollow(String id, AlarmDTO alarm) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "insert into alarm values(alarm_seq.nextval, (select user_seq from member where user_id = ?), ?, ?, ?)";

        System.out.println(id);
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
    public int insertDips(String id, AlarmDTO alarm) throws SQLException {
        return 0;
    }

    @Override
    public List<AlarmDTO> selectAlarm(String id) throws SQLException {
        return List.of();
    }

    @Override
    public int updateAlarm(String id, AlarmDTO alarm) throws SQLException {
        return 0;
    }
}
