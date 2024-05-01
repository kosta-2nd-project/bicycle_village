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
		
		HttpSession session = request.getSession();
		
		int userSeq = (int) session.getAttribute("user_seq");
		int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));
		
		BookmarkEntity bookmarkEntity = new BookmarkEntity.Builder().bookmarkSeq(0).userSeq(userSeq).boardSeq(boardSeq).build();
		
		int check = 0;
		
		int result = bookmarkService.BookmarkCheck(bookmarkEntity);
		if(result==1) {//이미 찜이 되어있음
			check = 1;
		}else {//추가
			bookmarkService.AddBookmark(bookmarkEntity);
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(check);
		
		PrintWriter out = response.getWriter();
		out.print(json);
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
