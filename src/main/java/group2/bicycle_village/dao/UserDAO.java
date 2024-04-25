package group2.bicycle_village.dao;

import java.sql.SQLException;

import group2.bicycle_village.common.dto.UserDTO;

public interface UserDAO {
	   /**
	   * 로그인 기능
	   * */
		UserDTO loginCheck(UserDTO userDTO)throws SQLException;
		
		/**
		 * 회원가입 기능
		 */
		int insert(UserDTO userDTO) throws SQLException;
		
		/**
		 * 아이디에 해당하는 회원 찾기
		 */
		UserDTO selectById(String id) throws SQLException;
		
		/**
		 * 회원정보 수정 기능
		 */
		int updateInfo(UserDTO userDTO) throws SQLException;
		
		/**
		 * 회원탈퇴 기능
		 */
		int delAccount(UserDTO userDTO) throws SQLException;
		
		/**
		 * 회원 상태 변경
		 */
		int changeStatus(UserDTO userDTO) throws SQLException;
}
