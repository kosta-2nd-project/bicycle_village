package group2.bicycle_village.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import group2.bicycle_village.common.dto.BoardEntity;
import group2.bicycle_village.common.dto.BookmarkEntity;
import group2.bicycle_village.common.dto.BookmarkListDTO;
import group2.bicycle_village.service.BookmarkService;
import group2.bicycle_village.service.BookmarkServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BookmarkController implements RestController {
	
	private BookmarkService bookmarkService = new BookmarkServiceImpl(); 

	
	/**
	 * 찜 추가 기능
	 */
	public void addBookmark(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int userSeq = Integer.parseInt(request.getParameter("userSeq"));
		int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));
		
		bookmarkService.AddBookmark(new BookmarkEntity.Builder().bookmarkSeq(0).userSeq(userSeq).boardSeq(boardSeq).build());

	}
	
	/**
	 * 찜 삭제 기능
	 */
	public void delBookmark(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int boardSeq = Integer.parseInt(request.getParameter("seq"));
		System.out.println(boardSeq);
		HttpSession session = request.getSession();
		int userSeq = (int)session.getAttribute("user_seq");
		bookmarkService.DeleteBookmark(new BookmarkEntity.Builder().boardSeq((long)boardSeq).userSeq((long)userSeq).build());
	}
	
	/**
	 * 전체 조회
	 */
	public void selectAll(HttpServletRequest req, HttpServletResponse res) throws Exception{
		System.out.println("Bookmark Controller selectAll call...");
		HttpSession session = req.getSession();
		int userSeq = (int) session.getAttribute("user_seq");
		System.out.println(userSeq);
		
		List<BookmarkListDTO> list = bookmarkService.selectAll(userSeq);
		
		System.out.println(list);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		PrintWriter out = res.getWriter();
		out.print(json);
	}
}
