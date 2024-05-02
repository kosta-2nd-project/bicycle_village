package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.MessageDTO;

import java.sql.SQLException;
import java.util.Date;

public interface MessageDao {

    int insertMessage(int roomId, String message, String userId) throws SQLException;


}

