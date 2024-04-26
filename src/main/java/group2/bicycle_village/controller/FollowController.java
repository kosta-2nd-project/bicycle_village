package group2.bicycle_village.controller;

import group2.bicycle_village.common.dto.followEntity;
import group2.bicycle_village.service.FollowService;
import group2.bicycle_village.service.FollowServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FollowController implements RestController {
	
	private FollowService followService = new FollowServiceImpl();
	
	/**
	 * 팔로우 추가 기능
	 */
	public void addFollow(HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		int follow = Integer.parseInt(req.getParameter("follow"));
		int follower = Integer.parseInt(req.getParameter("follower"));
		
		followService.addFollow(new followEntity.Builder().followSeq(0).follow(follow).follower(follower).build());
	}
	
	/**
	 * 찜 삭제 기능
	 */
	public void delFollow(HttpServletRequest req, HttpServletResponse res) throws Exception{
		int followSeq = Integer.parseInt(req.getParameter("followSeq"));
		followService.delFollow(new followEntity.Builder().followSeq(followSeq).follow(0).follower(0).build());
	}

}
