package group2.bicycle_village.controller;

import com.google.gson.Gson;
import group2.bicycle_village.common.dto.FollowEntity;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.service.FollowService;
import group2.bicycle_village.service.FollowServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.util.List;

public class FollowController implements RestController {
	
	private FollowService followService = new FollowServiceImpl();
	
	/**
	 * 팔로우 추가 기능
	 */
	public void addFollow(HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		int follow = Integer.parseInt(req.getParameter("follow"));
		int follower = Integer.parseInt(req.getParameter("follower"));
		
		followService.addFollow(new FollowEntity.Builder().followSeq(0).follow(follow).follower(follower).build());
	}
	
	/**
	 * 팔로우 삭제 기능
	 */
	public void delFollow(HttpServletRequest req, HttpServletResponse res) throws Exception{
		String followId = req.getParameter("followId");
		System.out.println("Controller followId: " + followId);
		int result = followService.delFollow(followId);

		PrintWriter out = res.getWriter();
		out.println(result);
	}

	/**
	 * 팔로우 목록 조회 기능
	 */
	public void selectAll(HttpServletRequest req, HttpServletResponse res) throws Exception{
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("loginId");
		List<UserDTO> followList = followService.selectAll(id);

		Gson gson = new Gson();
		String json = gson.toJson(followList);

		PrintWriter out = res.getWriter();
		out.print(json);
	}

}
