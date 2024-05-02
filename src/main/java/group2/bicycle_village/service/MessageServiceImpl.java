package group2.bicycle_village.service;

import group2.bicycle_village.common.dto.MessageDTO;
import group2.bicycle_village.dao.MessageDao;
import group2.bicycle_village.dao.MessageDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class MessageServiceImpl implements MessageService{

    MessageDao messageDao= new MessageDaoImpl();

    @Override
    public int insertMessage(int roomId,  String userId,String message) throws SQLException {

      int result=  messageDao.insertMessage(roomId,userId, message);

        return result;

    }

    @Override
    public List<MessageDTO> findListByRoomId(int roomId) throws SQLException {
        List<MessageDTO> list = messageDao.findListByRoomId(roomId);
        return list;
    }
}
