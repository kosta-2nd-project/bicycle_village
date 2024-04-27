package group2.bicycle_village.dao;

import java.sql.SQLException;

import group2.bicycle_village.common.dto.followEntity;

public interface FollowDAO {
	/**
	 * 팔로우 추가 기능
	 */
	int addFollow(followEntity follow) throws SQLException;
	
	/**
	 * 팔로우 삭제 기능
	 */
	int delFollow(followEntity follow) throws SQLException;
}
