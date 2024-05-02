package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.MessageDTO;
import group2.bicycle_village.common.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class MessageDaoImpl implements MessageDao {
    @Override
    public int insertMessage(int roomId,  String userId, String message) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
//        String sql= "insert into MESSAGES (MESSEAGE_SEQ, ROOM_ID, USER_ID, MESSAGE) values (MESSAGES_SEQ.nextval, ?, ?, ?";
        String sql= "insert into MESSAGES (MESSEAGE_SEQ, ROOM_ID, USER_ID, MESSAGE) values (MESSAGES_SEQ.nextval, ?, ?, ?)";

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,roomId);
            ps.setString(2, userId);
            ps.setString(3, message);
            result=ps.executeUpdate();


        } finally {
            DbUtil.close(con,ps,null);
        }

        return result;
    }
}
