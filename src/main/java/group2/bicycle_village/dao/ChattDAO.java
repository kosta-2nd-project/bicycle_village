package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.ChattDTO;

import java.sql.SQLException;
import java.util.List;


public interface ChattDAO {


    int insert (ChattDTO chattDTO) throws SQLException;


    int updateChat(String content , String roomUrl) throws  SQLException ;


    List<ChattDTO> findChatList ( String userId) throws  SQLException;
}
