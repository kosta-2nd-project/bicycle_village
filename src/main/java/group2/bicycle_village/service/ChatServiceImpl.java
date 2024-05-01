package group2.bicycle_village.service;

import group2.bicycle_village.common.dto.ChattDTO;
import group2.bicycle_village.dao.ChattDAO;
import group2.bicycle_village.dao.ChattDAOImpl;
import group2.bicycle_village.exception.AuthenticationException;

import java.sql.SQLException;
import java.util.List;

public class ChatServiceImpl implements ChattService {

    ChattDAO chattDAO = new ChattDAOImpl();

    @Override
    public int insert(ChattDTO chattDTO) throws SQLException, AuthenticationException {
        int result =chattDAO.insert(chattDTO);

        if(result==1){
            throw new AuthenticationException("채팅방 생성 실패");

        }

        return result;
    }

    @Override
    public int updateChat(String content, String roomUrl) throws SQLException , AuthenticationException {
        int result =chattDAO.updateChat(content,roomUrl);
        if(result==1){
            throw new AuthenticationException("채팅 업데이트 실패");
        }
        return result;
    }

    @Override
    public List<ChattDTO> findChatList(String userId) throws SQLException, AuthenticationException {

        List<ChattDTO> chattDTOList= chattDAO.findChatList(userId);

        return chattDTOList;
    }
}
