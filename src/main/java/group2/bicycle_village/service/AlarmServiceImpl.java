package group2.bicycle_village.service;

import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.dao.AlarmDAO;
import group2.bicycle_village.dao.AlarmDAOImpl;

import java.sql.SQLException;

public class AlarmServiceImpl implements AlarmService {
    private AlarmDAO alarmDAO = new AlarmDAOImpl();
    @Override
    public int insertFollow(String id, AlarmDTO alarm) throws SQLException {
        return alarmDAO.insertFollow(id, alarm);
    }
}
