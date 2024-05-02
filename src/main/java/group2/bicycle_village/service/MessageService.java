package group2.bicycle_village.service;

import java.sql.SQLException;

public interface MessageService {
    int insertMessage(int roomId, String message, String userId) throws SQLException;
}
