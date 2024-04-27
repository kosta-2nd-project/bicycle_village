package group2.bicycle_village.service;

import java.sql.SQLException;

import group2.bicycle_village.common.dto.followEntity;
import group2.bicycle_village.exception.AuthenticationException;

public interface FollowService {

	/**
	 * 팔로우 추가
	 */
	void addFollow(followEntity follow) throws SQLException, AuthenticationException;
	
	/**
	 * 팔로우 삭제
	 */
	void delFollow(followEntity follow) throws SQLException, AuthenticationException;
}
