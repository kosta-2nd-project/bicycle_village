package group2.bicycle_village.controller;

import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import group2.bicycle_village.common.dto.FollowEntity;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.service.FollowService;
import group2.bicycle_village.service.FollowServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FollowController implements RestController {
	
	private FollowService followService = new FollowServiceImpl();
	
	/**
	 * 팔로우 추가 기능
	 */
	public void addFollow(HttpServletRequest req, HttpServletResponse res) throws Exception{
		HttpSession session = req.getSession();
		int follower = (int) session.getAttribute("user_seq");
		String userId = req.getParameter("userId");
		int check = 0;
		
		int userSeq = followService.checkUser(userId);
		
		FollowEntity followEntity = new FollowEntity.Builder().follow(userSeq).follower(follower).build();
		int result = followService.FollowCheck(followEntity);
		if(result == 1) {//이미 팔로우
			check = 1;
		}else {
			followService.addFollow(followEntity);
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(check);
		
		PrintWriter out = res.getWriter();
		out.print(json);
	}
	
	/**
	 * 팔로우 삭제 기능
	 */
	public void delFollow(HttpServletRequest req, HttpServletResponse res) throws Exception{
		int followSeq = Integer.parseInt(req.getParameter("followSeq"));
		String followId = req.getParameter("followId");
		System.out.println("Controller followId: " + followId);
		int result = followService.delFollow(followId);

		PrintWriter out = res.getWriter();
		out.println(result);
	}

	/**
	 * 팔로우 목록 조회 기능
	 */
	public void selectAll(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("loginId");
		List<UserDTO> followList = followService.selectAll(id);

		Gson gson = new Gson();
		String json = gson.toJson(followList);

		PrintWriter out = res.getWriter();
		out.print(json);
	}

}
