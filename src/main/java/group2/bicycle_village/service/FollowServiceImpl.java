package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group2.bicycle_village.common.dto.FollowEntity;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.dao.AlarmDAO;
import group2.bicycle_village.dao.AlarmDAOImpl;
import group2.bicycle_village.dao.FollowDAO;
import group2.bicycle_village.dao.FollowDAOImpl;
import group2.bicycle_village.exception.AuthenticationException;

public class FollowServiceImpl implements FollowService {
	
	FollowDAO followDao = new FollowDAOImpl();
	AlarmDAO alarmDao = new AlarmDAOImpl();

	@Override
	public void addFollow(FollowEntity follow) throws SQLException, AuthenticationException {
		int result = followDao.addFollow(follow);
		if(result==0) {
			throw new AuthenticationException("팔로우 추가 실패");
		}

	}

	@Override
	public void delFollow(FollowEntity follow) throws SQLException, AuthenticationException {
		int result = followDao.delFollow(follow);
		if(result==0) {
			throw new AuthenticationException("팔로우 삭제 실패");
		}
	}

	@Override
	public List<UserDTO> selectAll(String id) throws SQLException, AuthenticationException {
//		List<UserDTO> userList = null;
		if(id.equals("")) {
			throw new AuthenticationException("팔로우 목록 조회 실패");
		} /*else {
			List<FollowEntity> followList = followDao.selectAllFollow(id);
			followList.forEach(c -> {
                try {
                    userList.add(alarmDao.userIdAndNickname(c.getFollow()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });




			for(FollowEntity follow : followList) {
				System.out.println("follow : "+follow.getFollow());
				userList.add(alarmDao.userIdAndNickname(follow.getFollow()));
			}
		}*/
		return followDao.searchNicknameUserId(id);
	}
}
