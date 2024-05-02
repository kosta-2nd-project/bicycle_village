package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.MessageDTO;
import group2.bicycle_village.common.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public List<MessageDTO> findListByRoomId(int roomId) throws SQLException {
        Connection con =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<MessageDTO> list= new ArrayList<>();
        String sql="select * from  MESSAGES where ROOM_ID=?";
        try {
            con = DbUtil.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, roomId );
            rs=ps.executeQuery();
            while (rs.next()){
                MessageDTO messageDTO =new MessageDTO(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4), rs.getDate(5));
                list.add(messageDTO);
            }
        } finally {DbUtil.close(con,ps,rs);
        }

       return list;
    }
}
