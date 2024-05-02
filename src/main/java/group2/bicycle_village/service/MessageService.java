package group2.bicycle_village.service;

import group2.bicycle_village.common.dto.MessageDTO;

import java.sql.SQLException;
import java.util.List;

public interface MessageService {
    int insertMessage(int roomId, String message, String userId) throws SQLException;

    List<MessageDTO> findListByRoomId(int roomId) throws SQLException;

}
