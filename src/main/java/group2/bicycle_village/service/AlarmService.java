package group2.bicycle_village.service;

import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.dao.AlarmDAO;

import java.sql.SQLException;
import java.util.List;

public interface AlarmService {
    int insertFollow(String id, AlarmDTO alarm) throws SQLException;

    int insertDips(String id, AlarmDTO alarm) throws SQLException;

    List<AlarmDTO> selectAllAlarm(String id) throws SQLException;

    int updateAlarm(String id, AlarmDTO alarm) throws SQLException;

    List<String> linked(String linkURL) throws SQLException;
}
