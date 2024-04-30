package group2.bicycle_village.controller;

import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.service.IndexPostService;
import group2.bicycle_village.service.IndexPostServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndexPostController implements RestController{
	
	IndexPostService indexService = new IndexPostServiceImpl();

	/**
	 * 조회수 상위 5개 게시물 조회
	 */
	public void selectBest(HttpServletRequest req, HttpServletResponse res) throws Exception{
		System.out.println("IndexPostController selectBest call...");
		List<BoardDTO> list = indexService.selectBest();
		
		System.out.println(list);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		PrintWriter out = res.getWriter();
		out.print(json);
	}
}
