package group2.bicycle_village.service;

import java.sql.SQLException;

import group2.bicycle_village.common.dto.followEntity;
import group2.bicycle_village.dao.FollowDAO;
import group2.bicycle_village.dao.FollowDAOImpl;
import group2.bicycle_village.exception.AuthenticationException;

public class FollowServiceImpl implements FollowService {
	
	FollowDAO followDao = new FollowDAOImpl();

	@Override
	public void addFollow(followEntity follow) throws SQLException, AuthenticationException {
		int result = followDao.addFollow(follow);
		if(result==0) {
			throw new AuthenticationException("팔로우 추가 실패");
		}

	}

	@Override
	public void delFollow(followEntity follow) throws SQLException, AuthenticationException {
		int result = followDao.delFollow(follow);
		if(result==0) {
			throw new AuthenticationException("팔로우 삭제 실패");
		}
	}

}
