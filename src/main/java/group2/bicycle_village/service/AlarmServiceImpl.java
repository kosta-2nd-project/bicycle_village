package group2.bicycle_village.service;

import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.dao.AlarmDAO;
import group2.bicycle_village.dao.AlarmDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class AlarmServiceImpl implements AlarmService {
    private AlarmDAO alarmDAO = new AlarmDAOImpl();
    @Override
    public int insertFollow(String id, AlarmDTO alarm) throws SQLException {
        int result = 0;
        List<UserDTO> follower = alarmDAO.searchFollower(id);
        for (UserDTO user : follower) {
            result += alarmDAO.insertFollow(user.getUserId(), alarm);
        }
        return result;
    }

    @Override
    public int insertDips(String id, AlarmDTO alarm) throws SQLException {
        return 0;
    }

    @Override
    public List<AlarmDTO> selectAllAlarm(String id) throws SQLException {
        return List.of();
    }

    @Override
    public int updateAlarm(String id, AlarmDTO alarm) throws SQLException {
        return 0;
    }

    @Override
    public List<String> linked(String linkURL) throws SQLException {
        return List.of();
    }
}
