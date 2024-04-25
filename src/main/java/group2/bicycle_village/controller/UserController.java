package group2.bicycle_village.controller;

import java.io.IOException;

import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.service.UserService;
import group2.bicycle_village.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserController implements Controller {
	
	private UserService userService = new UserServiceImpl();

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 로그인 기능
	 */
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String errMsg=null;
		boolean isRedirect = false;
		
		String userId =request.getParameter("userId");
		String pw =request.getParameter("pwd");
		
		int result = userService.IdCheck(userId);
		
		if(userId==null || userId.equals("") || pw==null || pw.equals("")) {
			errMsg = "아이디와 비밀번호를 모두 입력해주세요.";
		}else if(result==1) {
			errMsg="존재하지 않는 아이디입니다.";
		}else if(result==2) {
			//회원탈퇴 신청한지 90일 이내
			UserDTO userDTO = userService.loginCheck(new UserDTO(userId,pw));
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", userDTO);
			session.setAttribute("user_seq", userDTO.getUser_seq());
			session.setAttribute("loginName", userDTO.getName());
			session.setAttribute("loginId", userDTO.getUserId());
			session.setAttribute("loginPw", userDTO.getPwd());
			session.setAttribute("nickName", userDTO.getNickName());
			session.setAttribute("loginTel", userDTO.getTel());
			session.setAttribute("loginBirth", userDTO.getBirth());
			session.setAttribute("loginEmail", userDTO.getEmail());
			session.setAttribute("UserStatus", userDTO.getStatus());
			session.setAttribute("loginGender", userDTO.getGender());
			
			return new ModelAndView("loginMsg.jsp",false);
			
		}else {
			UserDTO dbDTO = userService.loginCheck( new UserDTO(userId, pw));
			
			//로그인성공하면 세션에 정보 저장
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", dbDTO);
			session.setAttribute("user_seq", dbDTO.getUser_seq());
			session.setAttribute("loginName", dbDTO.getName());
			session.setAttribute("loginId", dbDTO.getUserId());
			session.setAttribute("loginPw", dbDTO.getPwd());
			session.setAttribute("nickName", dbDTO.getNickName());
			session.setAttribute("loginTel", dbDTO.getTel());
			session.setAttribute("loginBirth", dbDTO.getBirth());
			session.setAttribute("loginEmail", dbDTO.getEmail());
			session.setAttribute("UserStatus", dbDTO.getStatus());
			
			isRedirect = true;
		}
		
		if(isRedirect) {
			//index.jsp이동 - redirect방식
    		return new ModelAndView("home.jsp",true);
    	}else {
    		request.setAttribute("errMsg", errMsg);
    		return new ModelAndView("errorMsg.jsp", false);
    	}
		
	}//login End
	
	/**
	 * 로그아웃
	 */
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//모든 세션의정보를 삭제
		request.getSession().invalidate();
		
		return new ModelAndView("home.jsp", true);
	
	}//logout End
	
	/**
	 * 회원가입
	 */
	public ModelAndView signUp(HttpServletRequest req, HttpServletResponse response)
			throws Exception {
			
			String errMsg = "";
			boolean isRedirect = false;
			
			//전송되는 값들을 받는다.
			String userId =req.getParameter("userId");
			String pw =req.getParameter("pwd");
			String pw2 = req.getParameter("pwd2");
			String name = req.getParameter("name");
			String nickName = req.getParameter("name2");
			String tel = req.getParameter("tel");
			String birth = req.getParameter("birth");
			String email = req.getParameter("email");
			String gender = req.getParameter("gender");
			
			int result = userService.IdCheck(userId);
			
			if(userId==null || userId.equals("") || pw==null || pw.equals("") ||
					pw2==null || pw2.equals("") || name==null || name.equals("") ||
					nickName==null || nickName.equals("") || tel==null || tel.equals("") ||
					birth==null || birth.equals("")){
				//유효성 체크
				errMsg = "입력값들의 정보가 충분하지 않습니다. 다시 확인해주세요.";
			}else if(pw2.equals(pw)!=true){
				//비밀번호 재확인
				errMsg = "비밀번호를 다시 확인해주세요!";
			}
			else if(result==0) {
				//아이디 중복 체크
				errMsg = "이미 사용중인 아이디입니다.";
			}else {
				userService.signUp(new UserDTO(userId, pw, name, nickName, tel, birth, email, gender,0));
				isRedirect = true;
			}
			
			if(isRedirect) {
	    		return new ModelAndView("home.jsp",true);
	    	}else {
	    		req.setAttribute("errMsg", errMsg);
	    		return new ModelAndView("errorMsg.jsp", false);
	    	}
		}//Sign up End
	
	/**
	 * 회원정보 수정
	 */
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res) throws Exception{
			
			String errMsg = "";
			boolean isRedirect = false;
			
			String userId =req.getParameter("userId");
			String pw =req.getParameter("pwd");
			String pw2 = req.getParameter("pwd2");
			String name = req.getParameter("name");
			String nickName = req.getParameter("name2");
			String tel = req.getParameter("tel");
			String birth = req.getParameter("birth");
			String email = req.getParameter("email");
			String gender = req.getParameter("gender");
			
			if(pw==null || pw.equals("") || pw2==null || pw2.equals("") ||
					nickName==null || nickName.equals("") || tel==null || tel.equals("")){
				//유효성 체크
				errMsg = "입력값들의 정보가 충분하지 않습니다. 다시 확인해주세요.";
			}else if(pw2.equals(pw)!=true){
				//비밀번호 재확인
				errMsg = "비밀번호를 다시 확인해주세요!";
			}else {
				userService.UpdateInfo(new UserDTO(userId, pw, name, nickName, tel, birth, email, gender,0));
				isRedirect = true;
			}
			
			if(isRedirect) {
	    		return new ModelAndView("home.jsp",true);
	    	}else {
	    		req.setAttribute("errMsg", errMsg);
	    		return new ModelAndView("errorMsg.jsp", false);
	    	}
		}//회원정보 수정 End
	
		/**
		 * 회원 상태 변경 (탈퇴 - > 정상)
		 */
		public ModelAndView changeStatus(HttpServletRequest req, HttpServletResponse res) throws Exception{
			HttpSession session = req.getSession();
			userService.changeStatus(new UserDTO((String)session.getAttribute("loginId"),(String)session.getAttribute("loginPw")));
			return new ModelAndView("home.jsp",true);
		}// changeStatus End
		
		/**
		 * 회원탈퇴
		 */
		public ModelAndView unregist(HttpServletRequest req, HttpServletResponse res) throws Exception{
			
			System.out.println("unregist call");
			HttpSession session = req.getSession();
			
			userService.delAccount(new UserDTO((String)session.getAttribute("loginId"), (String)session.getAttribute("loginPw")));
			
			//모든 세션의정보를 삭제
			session.invalidate();
					
			return new ModelAndView("home.jsp", true);
		}// 회원탈퇴 End
}
