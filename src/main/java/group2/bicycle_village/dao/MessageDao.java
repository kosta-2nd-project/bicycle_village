package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.MessageDTO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface MessageDao {

    int insertMessage(int roomId, String message, String userId) throws SQLException;


    List<MessageDTO> findListByRoomId (int roomId ) throws  SQLException;



}

