package group2.bicycle_village.controller;

import java.io.PrintWriter;

import com.google.gson.Gson;

import group2.bicycle_village.common.dto.FollowEntity;
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
		followService.delFollow(new FollowEntity.Builder().followSeq(followSeq).follow(0).follower(0).build());
	}

}
