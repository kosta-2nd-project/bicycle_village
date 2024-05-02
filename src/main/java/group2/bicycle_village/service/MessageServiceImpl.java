package group2.bicycle_village.service;

import group2.bicycle_village.dao.MessageDao;
import group2.bicycle_village.dao.MessageDaoImpl;

import java.sql.SQLException;

public class MessageServiceImpl implements MessageService{

    MessageDao messageDao= new MessageDaoImpl();

    @Override
    public int insertMessage(int roomId,  String userId,String message) throws SQLException {

      int result=  messageDao.insertMessage(roomId,userId, message);

        return result;

    }
}
