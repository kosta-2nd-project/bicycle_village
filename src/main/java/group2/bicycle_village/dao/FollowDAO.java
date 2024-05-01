package group2.bicycle_village.dao;

import java.sql.SQLException;

import group2.bicycle_village.common.dto.FollowEntity;

public interface FollowDAO {
	/**
	 * 팔로우 추가 기능
	 */
	int addFollow(FollowEntity follow) throws SQLException;
	
	/**
	 * 팔로우 삭제 기능
	 */
	int delFollow(FollowEntity follow) throws SQLException;
	
	/**
	 * user_seq 확인
	 */
	int checkUser(String userId) throws SQLException;
	
	/**
	 * 팔로우 여부 확인
	 */
	FollowEntity checkFollow(FollowEntity follow) throws SQLException;
}
