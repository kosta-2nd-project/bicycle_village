package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.FollowEntity;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.exception.AuthenticationException;

public interface FollowService {

	/**
	 * 팔로우 추가
	 */
	void addFollow(FollowEntity follow) throws SQLException, AuthenticationException;
	
	/**
	 * 팔로우 삭제
	 */
	int delFollow(String followId) throws SQLException, AuthenticationException;
	
	/**
	 * user_seq확인
	 */
	int checkUser(String nickname) throws SQLException, AuthenticationException;
	
	/**
	 * 팔로우 여부 조회
	 */
	public int FollowCheck(FollowEntity follow) throws SQLException, AuthenticationException;

	/**
	 * 팔로우 목록 보기
	 */
	List<UserDTO> selectAll(String id) throws SQLException, AuthenticationException;
}
