package group2.bicycle_village.service;

import java.sql.SQLException;

import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.dao.UserDAO;
import group2.bicycle_village.dao.UserDAOImpl;
import group2.bicycle_village.exception.AuthenticationException;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO = new UserDAOImpl();
  	
	@Override
	public UserDTO loginCheck(UserDTO userDTO) throws SQLException, AuthenticationException {
		
		//dao호출
		UserDTO dbDTO = userDAO.loginCheck(userDTO);
		if(dbDTO == null) {
			throw new AuthenticationException("정보를 다시 확인해주세요.");
		}
		
		return dbDTO;
	}

	@Override
	public void signUp(UserDTO userDTO) throws SQLException, AuthenticationException {
		int result = userDAO.insert(userDTO);
		if(result==0) {
			throw new AuthenticationException("회원가입 실패");
		}
	}

	@Override
	public int IdCheck(String id) throws SQLException, AuthenticationException {
		int result = 1;
		UserDTO user = userDAO.selectById(id);
		if(user!=null && user.getStatus()!=2) {
			result = 0;
		}else if(user!=null && user.getStatus()==2) {
			//회원이 탈퇴를 신청한 상태
			result = 2;
		}
		return result;
	}

	@Override
	public void UpdateInfo(UserDTO userDTO) throws SQLException, AuthenticationException {
		int result = userDAO.updateInfo(userDTO);
		if(result !=1) {
			throw new AuthenticationException("회원정보 수정 실패");
		}
		
	}

	@Override
	public void delAccount(UserDTO userDTO) throws SQLException, AuthenticationException {
		System.out.println(userDTO.getUserId());
		int result = userDAO.delAccount(userDTO);
		if(result!=1) {
			throw new AuthenticationException("회원 탈퇴 실패");
		}
	}

	@Override
	public void changeStatus(UserDTO userDTO) throws SQLException, AuthenticationException {
		int result = userDAO.changeStatus(userDTO);
		if(result!=1) {
			throw new AuthenticationException("회원 상태 변경 실패");
		}
		
	}
}
