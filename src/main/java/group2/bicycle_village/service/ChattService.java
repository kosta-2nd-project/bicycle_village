package group2.bicycle_village.service;

import group2.bicycle_village.common.dto.ChattDTO;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.exception.AuthenticationException;

import java.sql.SQLException;
import java.util.List;

public interface ChattService {

    int insert(ChattDTO chattDTO) throws SQLException, AuthenticationException;

    int updateChat(String content, String roomUrl) throws  SQLException, AuthenticationException;

    List<ChattDTO> findChatList (String userId) throws  SQLException, AuthenticationException;



}
