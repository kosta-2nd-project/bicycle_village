package group2.bicycle_village.service;

import com.google.gson.Gson;
import group2.bicycle_village.common.dto.ChattDTO;
import group2.bicycle_village.dao.ChattDAO;
import group2.bicycle_village.dao.ChattDAOImpl;
import group2.bicycle_village.exception.AuthenticationException;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class ChatServiceImpl implements ChattService {

    ChattDAO chattDAO = new ChattDAOImpl();

    @Override
    public int insert(String roomUrl, String sellerId) throws SQLException, AuthenticationException {
        int result =chattDAO.insert(roomUrl, sellerId);
        return result;
    }

    @Override
    public List<ChattDTO> findUrlByUserId(String userId) throws SQLException {


     List<ChattDTO> list=  chattDAO.findUrlByUserId(userId);
        System.out.println(list+"리스트 서비스");

        return list;
    }

    @Override
    public List<ChattDTO> findUrlBySellerId(String sellerId) throws SQLException {
     List<ChattDTO> list = chattDAO.findUrlBySellerId(sellerId);

        return list;
    }

//    @Override
//    public int updateChat(String content, String roomUrl) throws SQLException , AuthenticationException {
//        int result =chattDAO.updateChat(content,roomUrl);
//        if(result==1){
//            throw new AuthenticationException("채팅 업데이트 실패");
//        }
//        return result;
//    }
//
//    @Override
//    public List<ChattDTO> findChatList(String userId) throws SQLException, AuthenticationException {
//
//        List<ChattDTO> chattDTOList= chattDAO.findChatList(userId);
//
//        return chattDTOList;
//    }
}
