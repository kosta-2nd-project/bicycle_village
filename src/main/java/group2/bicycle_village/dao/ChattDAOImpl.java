package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.ChattDTO;
import group2.bicycle_village.common.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChattDAOImpl implements ChattDAO {
    @Override
    public int insert(String roomUrl, String sellerId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO CHATROOMS (ROOM_ID, ROOM_URL , SELLERID) VALUES (ROOM_SEQ.nextval, ?,?)";

        int result = 0;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, roomUrl);
            ps.setString(2,sellerId);
            result = ps.executeUpdate();
        } finally {
            DbUtil.close(con, ps, null);
        }


        return result;
    }

    @Override
    public int findRoomIDByUrl(String roomUrl)throws  SQLException {
        Connection con =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result=0;
        String sql = "select ROOM_ID from CHATROOMS where ROOM_URL=?";
        try {
            con = DbUtil.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1,roomUrl);
            rs= ps.executeQuery();
            if(rs.next()){
                result=rs.getInt(1);
            }
        } finally {DbUtil.close(con,ps,rs);
        }



        return result;

    }

//    @Override
//    public List<ChattDTO> findUrlByUserId(String userId) throws SQLException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<ChattDTO> list= new ArrayList<>();
//        String sql ="select c.room_url from  chatrooms c join messages m On c.room_id = m.room_id where m.user_id=? and c.room_id = m.room_id";
//
//        try {
//            con = DbUtil.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setString(1,userId);
//            rs= ps.executeQuery();
//
//            while(rs.next()){
//                ChattDTO chattDTO= new ChattDTO(rs.getInt(1),rs.getString(2),rs.getDate(3));
//                list.add(chattDTO);
//
//            }
//
//        } finally { DbUtil.close(con,ps,rs);
//        }
//        return list;
//    }
@Override
public List<ChattDTO> findUrlByUserId(String userId) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<ChattDTO> list= new ArrayList<>();
    // SQL 쿼리: user_id에 해당하는 chatroom의 room_url을 선택합니다.
    String sql ="SELECT * FROM chatrooms c JOIN messages m ON c.room_id = m.room_id WHERE m.user_id=?";

    try {
        con = DbUtil.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1,userId);
        rs= ps.executeQuery();

        while(rs.next()){
            // 여기서 ChattDTO 객체를 생성할 때 room_url만을 넘겨줍니다.
            // ChattDTO 생성자가 String roomUrl 만 받도록 구현되어야 합니다.
            ChattDTO chattDTO = new ChattDTO(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4)); // 수정된 부분
            list.add(chattDTO);
        }

    } finally {
        DbUtil.close(con,ps,rs);
    }
    return list;
}

    @Override
    public List<ChattDTO> findUrlBySellerId(String sellerId) throws  SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ChattDTO> list= new ArrayList<>();
        String sql ="select ROOM_URL,SELLERID from CHATROOMS where  SELLERID=?";

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,sellerId);
            rs= ps.executeQuery();
            while(rs.next()){
                ChattDTO chattDTO = new ChattDTO(rs.getString(1),rs.getString(2)); // 수정된 부분
                list.add(chattDTO);
            }
        }finally {
            DbUtil.close(con,ps,rs);

        }

        return list;
    }


//    @Override
//    public int updateChat(String content , String roomUrl)  throws  SQLException{
//        Connection con = null;
//        PreparedStatement ps = null;
//        String sql ="update chatting set content=? where roomUrl=?";
//        int result = 0;
//        try {
//            con= DbUtil.getConnection();
//            ps= con.prepareStatement(sql);
//            ps.setString(1, content);
//            ps.setString(2, roomUrl);
//            result= ps.executeUpdate();
//        } finally {
//            DbUtil.close(con, ps, null);
//        }
//        return 0;
//    }
//
//    @Override
//    public List<ChattDTO> findChatList(String userId)  throws SQLException {
//        Connection con =null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String sql = "select *  from chatting where sellerId=? or buyerId=?";
//        List<ChattDTO> chattDTOList = new ArrayList<>();
//        try {
//            con = DbUtil.getConnection();
//            ps= con.prepareStatement(sql);
//            ps.setString(1, userId);
//            ps.setString(2, userId);
//            rs= ps.executeQuery();
//            while (rs.next()){
//                ChattDTO chattDTO = new ChattDTO(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5));
//
//                chattDTOList.add(chattDTO);
//            }
//        } finally {
//            DbUtil.close(con,ps,rs);
//        }
//
//
//
//        return chattDTOList;
//    }
}
