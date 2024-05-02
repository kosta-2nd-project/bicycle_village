package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.ChattDTO;

import java.sql.SQLException;
import java.util.List;


public interface ChattDAO {


    int insert (String roomUrl, String sellerId) throws SQLException;

    int findRoomIDByUrl (String roomUrl) throws  SQLException;

    List<ChattDTO> findUrlByUserId (String userId) throws  SQLException;

    List<ChattDTO> findUrlBySellerId(String sellerId) throws  SQLException;

}
