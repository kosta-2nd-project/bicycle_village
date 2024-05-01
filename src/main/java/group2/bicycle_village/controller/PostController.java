package group2.bicycle_village.controller;

import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import group2.bicycle_village.common.dto.PostDTO;
import group2.bicycle_village.service.PostService;
import group2.bicycle_village.service.PostServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PostController implements RestController{
	
	private PostService postService = new PostServiceImpl();

	/**
	 * 전체 조회
	 */
	public void selectAll(HttpServletRequest req, HttpServletResponse res) throws Exception{
		System.out.println("PostController selectAll call...");
		HttpSession session = req.getSession();
		int userSeq = (int) session.getAttribute("user_seq");
		System.out.println(userSeq);
		
		List<PostDTO> list = postService.selectAll(userSeq);
		
		System.out.println(list);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		PrintWriter out = res.getWriter();
		out.print(json);
	}
	
	/**
	 * 게시글 삭제
	 */
	public void delPost(HttpServletRequest req, HttpServletResponse res) throws Exception{
		System.out.println("PostController delPost call...");
		int boardSeq = Integer.parseInt(req.getParameter("seq"));
		System.out.println(boardSeq);
		HttpSession session = req.getSession();
		int userSeq = (int)session.getAttribute("user_seq");
		System.out.println(userSeq);
		postService.delPost(new PostDTO(boardSeq, userSeq));
	}
}
