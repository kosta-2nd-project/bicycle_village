package group2.bicycle_village.service;

import java.sql.SQLException;

import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.exception.AuthenticationException;

public interface UserService {
	/**
	 * 로그인 체크
	 * */
   UserDTO loginCheck(UserDTO userDTO)throws SQLException , AuthenticationException;
   
   /**
    * 회원가입
    */
   void signUp(UserDTO userDTO) throws SQLException, AuthenticationException;
   
   /**
    * 아이디 체크
    */
   int IdCheck(String id) throws SQLException, AuthenticationException;
   
   /**
    * 회원정보 수정
    */
   void UpdateInfo(UserDTO userDTO) throws SQLException, AuthenticationException;
   
   /**
    * 회원탈퇴
    */
   void delAccount(UserDTO userDTO) throws SQLException, AuthenticationException;
   
   /**
    * 회원 상태 변경
    */
   void changeStatus(UserDTO userDTO) throws SQLException, AuthenticationException;
}
