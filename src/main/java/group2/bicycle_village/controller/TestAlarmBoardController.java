package group2.bicycle_village.controller;

import group2.bicycle_village.common.constant.CommonCode;
import group2.bicycle_village.common.dto.BoardEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TestAlarmBoardController {
    /**
     * 등록하기
     * */
//    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        //전송된 데이터 받기
//        int boardCount = Integer.parseInt(request.getParameter("board_count"));
//        int goodsPrice = Integer.parseInt(request.getParameter("goods_price"));
//        //HttpSession session = request.getSession(); //controller에서 현재 로그인한 세션값 얻어오기
//        long userSeq = Integer.parseInt(request.getParameter("user_seq"));
//        String boardName = request.getParameter("board_name");
//        String category = request.getParameter("category");
//        String isSeen = request.getParameter("is_seen");
//        String boardContent = request.getParameter("board_content");
//        String boardAddr = request.getParameter("board_addr");
//        long productSeq = Integer.parseInt(request.getParameter("product_seq"));
//
//        BoardEntity board = new BoardEntity.Builder().price(goodsPrice).userSeq(userSeq).boardCount(boardCount)
//                .boardName(boardName).category(CommonCode.BoardCategory.valueOf(category))
//                .isSeen(CommonCode.BoardStatus.valueOf(isSeen)).addr(boardAddr).content(boardContent)
//                .productSeq(productSeq).build(); // 주소,내용,카테고리만 추가된 dto 생성하는데 카테고리 내용에 매칭되는 int 저장
//
//        long boardSeq = boardService.searchBoardSeq(userSeq);
//        System.out.println(boardSeq);
//        String url = boardService.linkURL(boardSeq);
//        boardService.insert(board, url);
//
//
//        return new ModelAndView("front?key=board&methodName=selectAll", true);
//        //return new ModelAndView("front?key=board&methodName=selectByBoardSeq&boardSeq=", true); // 나중에
//
//    }

    /**
     * 수정완료
     * */
//    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        // 전송된 데이터 받기
//        long boardSeq = Integer.parseInt(request.getParameter("boardSeq"));
//        String boardName = request.getParameter("board_name");
//        String boardContent = request.getParameter("board_content");
//        int goodsPrice = Integer.parseInt(request.getParameter("goods_price"));
//
//
//        BoardEntity board = new BoardEntity.Builder().boardSeq(boardSeq).boardName(boardName).content(boardContent)
//                .price(goodsPrice).build(); // 주소,내용,카테고리만 추가된 dto 생성하는데 카테고리 내용에 매칭되는 int 저장
//
//        String url = boardService.linkURL(boardSeq);
//        boardService.update(board,url);
//
//        //수정이 완료가 된후....
//        ModelAndView mv = new ModelAndView();
//
//        //mv.setViewName("front?key=board&methodName=selectByBoardSeq&boardSeq="+boardSeq);
//        mv.setViewName("front?key=board&methodName=selectAll");
//
//        mv.setRedirect(true);
//        return mv;
//    }
}
