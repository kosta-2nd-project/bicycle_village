package group2.bicycle_village.service;

import group2.bicycle_village.common.dto.ChattDTO;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.exception.AuthenticationException;

import java.sql.SQLException;
import java.util.List;

public interface ChattService {

    int insert(String roomUrl, String sellerID) throws SQLException, AuthenticationException;


    List<ChattDTO> findUrlByUserId(String userId) throws SQLException;



    List<ChattDTO> findUrlBySellerId(String sellerId) throws SQLException;


}
