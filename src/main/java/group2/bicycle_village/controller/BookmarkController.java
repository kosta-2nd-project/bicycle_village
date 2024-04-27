package group2.bicycle_village.controller;

import java.io.IOException;
import java.io.PrintWriter;

import group2.bicycle_village.common.dto.BookmarkEntity;
import group2.bicycle_village.service.BookmarkService;
import group2.bicycle_village.service.BookmarkServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
		
	}
}
