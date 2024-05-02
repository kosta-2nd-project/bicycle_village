package group2.bicycle_village.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import group2.bicycle_village.common.constant.CommonCode;
import group2.bicycle_village.common.constant.CommonCode.BoardCategory;
import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.BoardEntity;
import group2.bicycle_village.common.dto.BoardFileDTO;
import group2.bicycle_village.common.dto.CommentEntity;
import group2.bicycle_village.common.dto.CommentsDTO;
import group2.bicycle_village.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;


public class BoardController implements Controller{
	BoardService boardService = new BoardServiceImpl();
	BoardFileService boardFileService = new BoardFileServiceImpl();
	AlarmService alarmService = new AlarmServiceImpl();
	
	public BoardController(){
		System.out.println("BoardController 생성자 호출...");
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Collection<Part> fileParts = request.getParts();
	    for (Part filePart : fileParts) {
	        String fileName = getFileName(filePart);
	        if (fileName != null) {
	            System.out.println("File Name: " + fileName);
	        }
	    }
	    return null;
	}
	
	//==============================게시판 등록===============================================

	
	/**
	 * 자유게시판 등록하기
	 * */
	public ModelAndView insertFreeBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 받기
		int boardCount = Integer.parseInt(request.getParameter("board_count"));
		int goodsPrice = Integer.parseInt(request.getParameter("goods_price"));
		//HttpSession session = request.getSession(); //controller에서 현재 로그인한 세션값 얻어오기
		long userSeq = Long.parseLong(request.getParameter("user_seq"));
		String boardName = request.getParameter("board_name");
		String category = request.getParameter("category");
		String isSeen = request.getParameter("is_seen");
		String boardContent = request.getParameter("board_content");
		String boardAddr = request.getParameter("board_addr");
		long productSeq = Long.parseLong(request.getParameter("product_seq"));
		
		BoardEntity board = new BoardEntity.Builder().price(goodsPrice).userSeq(userSeq).boardCount(boardCount)
				.boardName(boardName).category(CommonCode.BoardCategory.valueOf(category))
				.isSeen(CommonCode.BoardStatus.valueOf(isSeen)).addr(boardAddr).content(boardContent)
				.productSeq(productSeq).build(); // 주소,내용,카테고리만 추가된 dto 생성하는데 카테고리 내용에 매칭되는 int 저장
		
		boardService.insert(board);
		Long boardSeq = boardService.searchBoardSeq(userSeq);
		
		Collection<Part> fileParts = request.getParts(); // 여러 파일 가져오기
        String uploadPath = "/save/" + boardSeq;
        
        File directory = new File(uploadPath);
		
		if (!directory.exists()) {
			directory.mkdirs(); // 디렉토리 생성
		}
		
		int saveNumber = 0;
		

		for (Part filePart : fileParts) {

			if (filePart.getName().equals("files")) {
				String fileName = getFileName(filePart); // 업로드된 파일 이름 가져오기
				// 업로드된 파일 저장

				try (InputStream input = filePart.getInputStream()) {
					Files.copy(input, new File(uploadPath + File.separator + fileName).toPath());

				} catch (IOException e) { // 오류 처리
					return new ModelAndView("front?key=board&methodName=selectAllFreeBoard", true);
				}

				BoardFileDTO boardFileDTO = new BoardFileDTO(boardSeq, saveNumber, fileName);

				boardFileService.insert(boardFileDTO);
				System.out.println("ok\n");
				saveNumber++;
			}

		} // 업로드 완료 후 처리
		
		
		return new ModelAndView("front?key=board&methodName=selectAllFreeBoard", true);
		//return new ModelAndView("front?key=board&methodName=selectByBoardSeq&boardSeq=", true); // 나중에
		
	}// Part 객체에서 파일 이름 가져오기

	private String getFileName(final Part part) {
		//System.out.println("getFileName = " + part +" , getName = " + part.getName());
	    final String partHeader = part.getHeader("content-disposition");
	    //System.out.println("partHeader = " + partHeader);
	    
	   
	    for (String content : partHeader.split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	/**
	 * 거래게시판 등록하기
	 * */
	public ModelAndView insertTradeBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 받기
		int boardCount = Integer.parseInt(request.getParameter("board_count"));
		int goodsPrice = Integer.parseInt(request.getParameter("goods_price"));
		//HttpSession session = request.getSession(); //controller에서 현재 로그인한 세션값 얻어오기
		long userSeq = Long.parseLong(request.getParameter("user_seq"));
		String boardName = request.getParameter("board_name");
		String category = request.getParameter("category");
		String isSeen = request.getParameter("is_seen");
		String boardContent = request.getParameter("board_content");
		String boardAddr = request.getParameter("board_addr");
		long productSeq = Long.parseLong(request.getParameter("product_seq"));
		
		BoardEntity board = new BoardEntity.Builder().price(goodsPrice).userSeq(userSeq).boardCount(boardCount)
				.boardName(boardName).category(CommonCode.BoardCategory.valueOf(category))
				.isSeen(CommonCode.BoardStatus.valueOf(isSeen)).addr(boardAddr).content(boardContent)
				.productSeq(productSeq).build(); // 주소,내용,카테고리만 추가된 dto 생성하는데 카테고리 내용에 매칭되는 int 저장

		
		boardService.insert(board);
		Long boardSeq = boardService.searchBoardSeq(userSeq);
		
		Collection<Part> fileParts = request.getParts(); // 여러 파일 가져오기
        String uploadPath = "/save/" + boardSeq;
        
        File directory = new File(uploadPath);
		
		if (!directory.exists()) {
			directory.mkdirs(); // 디렉토리 생성
		}
		
		int saveNumber = 0;
		

		for (Part filePart : fileParts) {
			if (filePart.getName().equals("files")) {
				String fileName = getFileName(filePart); // 업로드된 파일 이름 가져오기
				// 업로드된 파일 저장
				try (InputStream input = filePart.getInputStream()) {
					Files.copy(input, new File(uploadPath + File.separator + fileName).toPath());

				} catch (IOException e) { // 오류 처리
					e.printStackTrace();
					return new ModelAndView("front?key=board&methodName=selectAllTradeBoard", true);
				}

				BoardFileDTO boardFileDTO = new BoardFileDTO(boardSeq, saveNumber, fileName);

				boardFileService.insert(boardFileDTO);
				System.out.println("ok\n");
				saveNumber++;
			}
		} // 업로드 완료 후 처리

		alarmService.insertAlarm(userSeq);
		long lastBoardSeq = alarmService.searchBoardSeq(userSeq);
		String url = alarmService.linkURL(lastBoardSeq, "selectByTradeBoardSeq");
		alarmService.setLinkURL(url);

		return new ModelAndView("front?key=board&methodName=selectAllTradeBoard", true);

		//return new ModelAndView("front?key=board&methodName=selectByBoardSeq&boardSeq=", true); // 나중에
		
	}

	/**
	 * 정보게시판 등록하기
	 * */
	public ModelAndView insertInfoBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 받기
		int boardCount = Integer.parseInt(request.getParameter("board_count"));
		int goodsPrice = Integer.parseInt(request.getParameter("goods_price"));
		//HttpSession session = request.getSession(); //controller에서 현재 로그인한 세션값 얻어오기
		long userSeq = Long.parseLong(request.getParameter("user_seq"));
		String boardName = request.getParameter("board_name");
		String category = request.getParameter("category");
		String isSeen = request.getParameter("is_seen");
		String boardContent = request.getParameter("board_content");
		String boardAddr = request.getParameter("board_addr");
		long productSeq = Long.parseLong(request.getParameter("product_seq"));
		
		BoardEntity board = new BoardEntity.Builder().price(goodsPrice).userSeq(userSeq).boardCount(boardCount)
				.boardName(boardName).category(CommonCode.BoardCategory.valueOf(category))
				.isSeen(CommonCode.BoardStatus.valueOf(isSeen)).addr(boardAddr).content(boardContent)
				.productSeq(productSeq).build(); // 주소,내용,카테고리만 추가된 dto 생성하는데 카테고리 내용에 매칭되는 int 저장
		
		boardService.insert(board);
		Long boardSeq = boardService.searchBoardSeq(userSeq);

		alarmService.insertAlarm(userSeq);
		long lastBoardSeq = alarmService.searchBoardSeq(userSeq);
		String url = alarmService.linkURL(lastBoardSeq,"selectByInfoBoardSeq");
		alarmService.setLinkURL(url);
		
		Collection<Part> fileParts = request.getParts(); // 여러 파일 가져오기
        String uploadPath = "/save/" + boardSeq;
        
        File directory = new File(uploadPath);
		
		if (!directory.exists()) {
			directory.mkdirs(); // 디렉토리 생성
		}
		
		int saveNumber = 0;
		
		for (Part filePart : fileParts) {
			if (filePart.getName().equals("files")) {
				String fileName = getFileName(filePart); // 업로드된 파일 이름 가져오기
				// 업로드된 파일 저장
				
				try (InputStream input = filePart.getInputStream()) {
					Files.copy(input, new File(uploadPath + File.separator + fileName).toPath());

				} catch (IOException e) { // 오류 처리
					return new ModelAndView("front?key=board&methodName=selectAllInfoBoard", true);
				}

				BoardFileDTO boardFileDTO = new BoardFileDTO(boardSeq, saveNumber, fileName);

				boardFileService.insert(boardFileDTO);
				System.out.println("ok\n");
				saveNumber++;
			}
		} // 업로드 완료 후 처리


		
		return new ModelAndView("front?key=board&methodName=selectAllInfoBoard", true);
		//return new ModelAndView("front?key=board&methodName=selectByBoardSeq&boardSeq=", true); // 나중에
		
	}// Part 객체에서 파일 이름 가져오기
	
	
	//==============================파일 등록/삭제===============================================
	
	/**
	 * 파일 등록
	 * */
	public ModelAndView insertFileToTradeBoard(int boardSeq, String imageName, int saveNumber) throws Exception {
		
		
		
		return new ModelAndView("front?key=board&methodName=selectAllTradeBoard", true);
	}
	
	//==============================게시판 목록 전체 조회===============================================

	
	/**
	 *  자유게시판 전체검색
	 * */
	public ModelAndView selectAllFreeBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		String pageNo = request.getParameter("pageNo");
//		if (pageNo == null || pageNo.equals("")) {
//			pageNo = "1";
//		}

		//List<BoardDTO> list = boardService.selectByCateory(1, Integer.parseInt(pageNo));
		List<BoardDTO> list = boardService.selectByCateory(1);
		
//		for(BoardDTO board : list) { // 게시글별 댓글 수
//			System.out.println("CommentListSize========"+boardService.getCommentListSize(board.getBoardSeq()));
//			//board.setCommentListSize(boardService.getCommentListSize(board.getBoardSeq()));
//		}
		
		request.setAttribute("list", list);// 뷰에서 ${list}
		//request.setAttribute("pageNo", pageNo); // 뷰에서 ${pageNo}

		return new ModelAndView("board/freeBoard/freeBoardList.jsp"); // forward방식으로 이동
	}
	
	/**
	 *  거래게시판 전체검색
	 * */
	public ModelAndView selectAllTradeBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		String pageNo = request.getParameter("pageNo");
//		if (pageNo == null || pageNo.equals("")) {
//			pageNo = "1";
//		}

		//List<BoardDTO> list = boardService.selectAll(Integer.parseInt(pageNo));
		List<BoardDTO> list = boardService.selectByCateory(3);
		
		request.setAttribute("list", list);// 뷰에서 ${list}
		//request.setAttribute("pageNo", pageNo); // 뷰에서 ${pageNo}

		return new ModelAndView("board/tradeBoard/tradeBoardList.jsp"); // forward방식으로 이동
	}
	
	
	/**
	 *  정보게시판 전체검색
	 * */
	public ModelAndView selectAllInfoBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		String pageNo = request.getParameter("pageNo");
//		if (pageNo == null || pageNo.equals("")) {
//			pageNo = "1";
//		}

		//List<BoardDTO> list = boardService.selectByCateory(2, Integer.parseInt(pageNo));
		List<BoardDTO> list = boardService.selectByCateory(2);
		
		request.setAttribute("list", list);// 뷰에서 ${list}
		//request.setAttribute("pageNo", pageNo); // 뷰에서 ${pageNo}

		return new ModelAndView("board/infoBoard/infoBoardList.jsp"); // forward방식으로 이동
	}
	
	//==============================게시글 검색===============================================

	/**
	 * 자유게시판 상세보기 
	 * */

	public ModelAndView selectByFreeBoardSeq(HttpServletRequest request, HttpServletResponse response) throws Exception {

		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		boolean state = request.getParameter("flag")==null ? true : false;
		
		String pageNo =  request.getParameter("pageNo");
		 
		 //두번째 인수 boolean 조회수 증가여부를판단할 인수(true이면, false이면 증가안함)
		BoardDTO board = boardService.selectByBoardSeq(boardSeq, state);
		request.setAttribute("board", board);
		request.setAttribute("pageNo", pageNo);
		
		
//		//----다운로드------
//		
//		// 1. 넘어오는 파일의 이름을 배열로 받기
//		System.out.println("fname == " + request.getParameterValues("fname"));
//		String[] fileNames = request.getParameterValues("fname");
//		
//		//request.getPart
//		// 2. 저장폴더의 실제 경로를 얻어오기
//		String saveDir = "/save/"+ board.getUserSeq() + "/" + boardSeq;
//		
//		// 반복문을 통해 각 파일 다운로드
//		for (String fName : fileNames) {
//			
//			File file = new File(saveDir, fName);
//			// 브라우져 별 파일이름에대한 한글인코딩설정
//			String encodedFileName;
//			if (request.getHeader("user-agent").indexOf("Trident") == -1) { // IE가 아닌경우
//				System.out.println(1);
//				encodedFileName = new String(file.getName().getBytes("UTF-8"), "8859_1");
//			} else {
//				System.out.println(2);
//				encodedFileName = new String(file.getName().getBytes("euc-kr"), "8859_1");
//			}
//			// 브라우져가 해석할수 있는 파일을 해석하지 않고 다운로드!!!
//			response.setHeader("Content-Disposition", "attachment;filename=\"" + encodedFileName + "\";");
//			// 폴더에서 파일이름에 해당하는 파일을 읽어서 클라이언트 브라우져에서 다운로드(출력=쓰기)
//			try (FileInputStream fi = new FileInputStream(file); ServletOutputStream so = response.getOutputStream()) {
//				byte[] buffer = new byte[1024];
//				int bytesRead;
//				while ((bytesRead = fi.read(buffer)) != -1) {
//					so.write(buffer, 0, bytesRead);
//				}
//			} catch (IOException e) {
//				// 파일 읽기 실패 처리
//				e.printStackTrace();
//			}
//		}
		
		selectAllComments(request,response);
		
		return new ModelAndView("board/freeBoard/freeBoard.jsp"); //forward방식 
	}
	
	/**
	 * 거래게시판 상세보기 
	 * */

	public ModelAndView selectByTradeBoardSeq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		boolean state = request.getParameter("flag")==null ? true : false;
		 
		String pageNo =  request.getParameter("pageNo");
		 
		 //두번째 인수 boolean 조회수 증가여부를판단할 인수(true이면, false이면 증가안함)
		BoardDTO board = boardService.selectByBoardSeq(boardSeq, state);
		request.setAttribute("board", board);
		request.setAttribute("pageNo", pageNo);
		
		selectAllComments(request,response);
		
		String imageName = boardFileService.selectImageNamesByBoardSeq(boardSeq);
		request.setAttribute("imageName", imageName);

		return new ModelAndView("board/tradeBoard/tradeBoardNew.jsp"); 	}
	
	/**
	 * 정보게시판 상세보기 
	 * */

	public ModelAndView selectByInfoBoardSeq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		boolean state = request.getParameter("flag")==null ? true : false;
		 
		String pageNo =  request.getParameter("pageNo");
		 
		 //두번째 인수 boolean 조회수 증가여부를판단할 인수(true이면, false이면 증가안함)
		BoardDTO board = boardService.selectByBoardSeq(boardSeq, state);
		request.setAttribute("board", board);
		request.setAttribute("pageNo", pageNo);
		
		selectAllComments(request,response);
		
		return new ModelAndView("board/infoBoard/infoBoard.jsp"); //forward방식 
	}
	
	//==============================게시판 수정===============================================

	
	/**
	 *  자유게시판 수정폼
	 * */
	public ModelAndView freeboardUpdateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		BoardDTO board = boardService.selectByBoardSeq(boardSeq, false);
		
		request.setAttribute("board", board);
		
		return new ModelAndView("board/freeBoard/freeBoardUpdate.jsp");//forward방식
	}
	
	/**
	 *  거래게시판 수정폼
	 * */
	public ModelAndView tradeboardUpdateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		BoardDTO board = boardService.selectByBoardSeq(boardSeq, false);
		
		request.setAttribute("board", board);
		
		return new ModelAndView("board/tradeBoard/tradeBoardUpdate.jsp");//forward방식
	}
	
	/**
	 *  정보게시판 수정폼
	 * */
	public ModelAndView infoboardUpdateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		BoardDTO board = boardService.selectByBoardSeq(boardSeq, false);
		
		request.setAttribute("board", board);
		
		return new ModelAndView("board/infoBoard/infoBoardUpdate.jsp");//forward방식
	}
	
	/**
	 * 자유게시판 수정완료
	 * */
	public ModelAndView updateFreeBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 전송된 데이터 받기
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		String boardName = request.getParameter("board_name");
		String boardContent = request.getParameter("board_content");
		int goodsPrice = Integer.parseInt(request.getParameter("goods_price"));
		long userSeq = Long.parseLong(request.getParameter("user_seq"));
		

		BoardEntity board = new BoardEntity.Builder().boardSeq(boardSeq).boardName(boardName).content(boardContent)
				.price(goodsPrice).build(); // 주소,내용,카테고리만 추가된 dto 생성하는데 카테고리 내용에 매칭되는 int 저장
		
		boardService.update(board);
		
		Collection<Part> fileParts = request.getParts(); // 여러 파일 가져오기
		String uploadPath = "/save/" + boardSeq; // 업로드할 디렉토리 경로 설정
		File directory = new File(uploadPath);
		if (!directory.exists()) {
			directory.mkdirs(); // 디렉토리 생성
		}
		for (Part filePart : fileParts) {
			String fileName = getFileName(filePart); // 업로드된 파일 이름 가져오기
			// 업로드된 파일 저장
			try (InputStream input = filePart.getInputStream()) {
				Files.copy(input, new File(uploadPath + File.separator + fileName).toPath());
			} catch (IOException e) { // 오류 처리
			}
		} // 업로드 완료 후 처리
		
		//수정이 완료가 된후....
		ModelAndView mv = new ModelAndView();
		
		//mv.setViewName("front?key=board&methodName=selectByBoardSeq&boardSeq="+boardSeq);

		mv.setViewName("front?key=board&methodName=selectAllFreeBoard");

	    mv.setRedirect(true);
		return mv;
	}
	
	/**
	 * 거래게시판 수정완료
	 * */
	public ModelAndView updateTradeBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 전송된 데이터 받기
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		String boardName = request.getParameter("board_name");
		String boardContent = request.getParameter("board_content");
		int goodsPrice = Integer.parseInt(request.getParameter("goods_price"));
		
		BoardEntity board = new BoardEntity.Builder().boardSeq(boardSeq).boardName(boardName).content(boardContent)
				.price(goodsPrice).build(); // 주소,내용,카테고리만 추가된 dto 생성하는데 카테고리 내용에 매칭되는 int 저장

		String url = alarmService.linkURL(boardSeq, "selectByInfoBoardSeq");
		boardService.update(board, url);
		
		//수정이 완료가 된후....
		ModelAndView mv = new ModelAndView();
		
		//mv.setViewName("front?key=board&methodName=selectByBoardSeq&boardSeq="+boardSeq);
		mv.setViewName("front?key=board&methodName=selectAllTradeBoard");
	    mv.setRedirect(true);
		return mv;
	}
	
	/**
	 * 정보게시판 수정완료
	 * */
	public ModelAndView updateInfoBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 전송된 데이터 받기
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		String boardName = request.getParameter("board_name");
		String boardContent = request.getParameter("board_content");
		int goodsPrice = Integer.parseInt(request.getParameter("goods_price"));
		
		BoardEntity board = new BoardEntity.Builder().boardSeq(boardSeq).boardName(boardName).content(boardContent)
				.price(goodsPrice).build(); // 주소,내용,카테고리만 추가된 dto 생성하는데 카테고리 내용에 매칭되는 int 저장
		
		boardService.update(board);
		
		//수정이 완료가 된후....
		ModelAndView mv = new ModelAndView();
		
		//mv.setViewName("front?key=board&methodName=selectByBoardSeq&boardSeq="+boardSeq);
		mv.setViewName("front?key=board&methodName=selectAllInfoBoard");
	    mv.setRedirect(true);
		return mv;
	}
	
	//==============================게시판 삭제===============================================

	
	/**
	 * 자유게시판 삭제
	 * 
	 * */
	public ModelAndView deleteFreeBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));

		boardService.delete(boardSeq);
		
		return new ModelAndView("front?key=board&methodName=selectAllFreeBoard", true);
	}
	
	/**
	 * 거래 게시판 삭제
	 * 
	 * */
	public ModelAndView deleteTradeBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		boardService.delete(boardSeq);
		
		return new ModelAndView("front?key=board&methodName=selectAllTradeBoard", true);
	}
	
	/**
	 * 정보 게시판 삭제
	 * 
	 * */
	public ModelAndView deleteInfoBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		boardService.delete(boardSeq);
		
		return new ModelAndView("front?key=board&methodName=selectAllInfoBoard", true);
	}
	

	//==============================댓글=====================================================
	
	/**
	 * 댓글 전체검색
	 * */
	public void selectAllComments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		
		List<CommentsDTO> commentList = boardService.getComment(boardSeq);
		
		request.setAttribute("commentList", commentList);// 뷰에서 ${list}
	}
	
	/**
	 * 댓글 입력
	 * */
	public ModelAndView insertComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Long parentSeq = Long.parseLong(request.getParameter("parent_comment"));//0
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		long userSeq = Long.parseLong(request.getParameter("userSeq"));
		String commentContent = request.getParameter("commentContent");
		
		String category = request.getParameter("category");
		
		CommentEntity comment =
				new CommentEntity.Builder().parentCommentSeq(parentSeq).boardSeq(boardSeq).userSeq(userSeq).commentContent(commentContent).build();
		
		boardService.insertComment(comment);
		
		if(category.equals("FREE"))
			return new ModelAndView("front?key=board&methodName=selectByFreeBoardSeq&boardSeq="+boardSeq); // 자유게시판으로 이동
		else if(category.equals("TRADE"))
			return new ModelAndView("front?key=board&methodName=selectByTradeBoardSeq&boardSeq="+boardSeq); // 거래게시판으로 이동
		else if(category.equals("INFORMATION"))
			return new ModelAndView("front?key=board&methodName=selectByInfoBoardSeq&boardSeq="+boardSeq); // 정보게시판으로 이동
		else 
			return null;
	}
	
	public ModelAndView deleteComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long commentSeq = Long.parseLong(request.getParameter("commentSeq"));
		
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		String category = request.getParameter("category");
		
		boardService.deleteComment(commentSeq);
		
		if(category.equals("FREE"))
			return new ModelAndView("front?key=board&methodName=selectByFreeBoardSeq&boardSeq="+boardSeq); // 자유게시판으로 이동
		else if(category.equals("TRADE"))
			return new ModelAndView("front?key=board&methodName=selectByTradeBoardSeq&boardSeq="+boardSeq); // 거래게시판으로 이동
		else if(category.equals("INFORMATION"))
			return new ModelAndView("front?key=board&methodName=selectByInfoBoardSeq&boardSeq="+boardSeq); // 정보게시판으로 이동
		else 
			return null;
	}
	
	public ModelAndView updateComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long commentSeq = Long.parseLong(request.getParameter("commentSeq"));
		String commentContent = request.getParameter("commentUpdatedContent");
		
		long boardSeq = Long.parseLong(request.getParameter("boardSeq"));
		String category = request.getParameter("category");
		
		CommentEntity comment =
				new CommentEntity.Builder().commentSeq(commentSeq).commentContent(commentContent).build();
		
		boardService.updateComment(comment);
		
		if(category.equals("FREE"))
			return new ModelAndView("front?key=board&methodName=selectByFreeBoardSeq&boardSeq="+boardSeq); // 자유게시판으로 이동
		else if(category.equals("TRADE"))
			return new ModelAndView("front?key=board&methodName=selectByTradeBoardSeq&boardSeq="+boardSeq); // 거래게시판으로 이동
		else if(category.equals("INFORMATION"))
			return new ModelAndView("front?key=board&methodName=selectByInfoBoardSeq&boardSeq="+boardSeq); // 정보게시판으로 이동
		else 
			return null;
	}

}
