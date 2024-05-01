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
    public int insert(ChattDTO chattDTO) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into chatting values (chat_Seq.nextval, ? , ?  , ? , ?, sysdate)";
        System.out.println(chattDTO.getBuyerId());
        System.out.println(chattDTO.getSellerId());
        System.out.println(chattDTO.getRoomUrl());
        System.out.println(chattDTO.getContent());
        int result = 0;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, chattDTO.getBuyerId());
            ps.setString(2, chattDTO.getSellerId());
            ps.setString(3, chattDTO.getRoomUrl());
            ps.setString(4, chattDTO.getContent());
            result = ps.executeUpdate();
        } finally {
            DbUtil.close(con, ps, null);
        }


        return result;
    }

    @Override
    public int updateChat(String content , String roomUrl)  throws  SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        String sql ="update chatting set content=? where roomUrl=?";
        int result = 0;
        try {
            con= DbUtil.getConnection();
            ps= con.prepareStatement(sql);
            ps.setString(1, content);
            ps.setString(2, roomUrl);
            result= ps.executeUpdate();
        } finally {
            DbUtil.close(con, ps, null);
        }
        return 0;
    }

    @Override
    public List<ChattDTO> findChatList(String userId)  throws SQLException {
        Connection con =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select *  from chatting where sellerId=? or buyerId=?";
        List<ChattDTO> chattDTOList = new ArrayList<>();
        try {
            con = DbUtil.getConnection();
            ps= con.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, userId);
            rs= ps.executeQuery();
            while (rs.next()){
                ChattDTO chattDTO = new ChattDTO(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getDate(5));

                chattDTOList.add(chattDTO);
            }
        } finally {
            DbUtil.close(con,ps,rs);
        }



        return chattDTOList;
    }
}
