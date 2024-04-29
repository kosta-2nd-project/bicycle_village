package group2.bicycle_village.service;

import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.dao.AlarmDAO;

import java.sql.SQLException;
import java.util.List;

public interface AlarmService {
//    int insertFollow(AlarmDTO alarm) throws SQLException;

//    int insertDips(AlarmDTO alarm) throws SQLException;

    List<AlarmDTO> selectAllAlarm(String id) throws SQLException;

    int updateAlarm(String id, long alarmSeq) throws SQLException;

    String linked(long alarm) throws SQLException;
}
