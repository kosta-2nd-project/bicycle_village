package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.FollowEntity;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.dao.FollowDAO;
import group2.bicycle_village.dao.FollowDAOImpl;
import group2.bicycle_village.exception.AuthenticationException;

public class FollowServiceImpl implements FollowService {
	
	FollowDAO followDao = new FollowDAOImpl();

	@Override
	public void addFollow(FollowEntity follow) throws SQLException, AuthenticationException {
		int result = followDao.addFollow(follow);
		if(result==0) {
			throw new AuthenticationException("팔로우 추가 실패");
		}

	}

	@Override
	public int delFollow(String followId) throws SQLException, AuthenticationException {
		int result = followDao.delFollow(followId);
		if(result==0) {
			throw new AuthenticationException("팔로우 삭제 실패");
		}
		return result;
	}

	@Override
	public int checkUser(String nickname) throws SQLException, AuthenticationException {
		return followDao.checkUser(nickname);
	}

	@Override
	public int FollowCheck(FollowEntity follow) throws SQLException, AuthenticationException {
		FollowEntity entity = followDao.checkFollow(follow);
		if(entity != null) { //이미 팔로우 중
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public List<UserDTO> selectAll(String id) throws SQLException, AuthenticationException {
		if(id.equals("")) {
			throw new AuthenticationException("팔로우 목록 조회 실패");
		}
		return followDao.searchNicknameUserId(id);
	}
}
