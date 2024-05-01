package group2.bicycle_village.controller;

import com.google.gson.Gson;
import group2.bicycle_village.service.ReviewService;
import group2.bicycle_village.service.ReviewServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ReviewController implements RestController, Controller {

    private static final ReviewService reviewService = new ReviewServiceImpl();
    private static Gson gson = new Gson();

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    /*
     * 페이지 반환 메서드
     * @description: 리뷰를 리스트로 모아 보는 페이지를 반환하는 메서드이다.
     * */
    public ModelAndView listPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("reviewer", request.getParameter("reviewer"));
        request.setAttribute("reviewing", request.getParameter("reviewing"));
        return new ModelAndView("pages/review/list.jsp");
    }

    /*
     * 페이지 반환 메서드
     * @description: 선택한 리뷰의 내용을 보는 페이지를 반환하는 메서드이다.
     * */
    public ModelAndView detailPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("reviewSeq", request.getParameter("reviewSeq"));
        return new ModelAndView("pages/review/detail.jsp");
    }

    /*
     * 페이지 반환 메서드
     * @description: 리뷰를 작성하는 페이지를 반환하는 메서드이다.
     * */
    public ModelAndView postDetailPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("pages/review/post-detail.jsp");
    }


    /*
     * 리뷰 작성 메서드
     * @description: 요청한 리뷰 생성을 처리하는 메서드이다.
     * */
    public void postReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
        reviewService.createReview(request);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    /*
     * 리뷰 리스트로 조회 메서드
     * @description: 리뷰를 리스트로 반환하는 메서드로 작성자 기준 / 리뷰받은 사람 기준 중 하나의 기준으로 리뷰 리스트를 반환하는 메서드이다.
     * */
    public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().print(gson.toJson(reviewService.getReviewListByUserSeq(request)));
    }

    /*
     * 리뷰 상세 조회 메서드
     * @description: 선택한 리뷰의 값을 반환하는 메서드이다.
     * */
    public void getReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().print(gson.toJson(reviewService.getReview(request)));
    }

    /*
     * 리뷰 수정 메서드
     * @description: 리뷰를 수정하는 메서드이다.
     * */
    public void putReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
        reviewService.updateReview(request);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /*
     * 리뷰 삭제 메서드
     * @description: 선택한 리뷰를 삭제하는 메서드이다.
     * */
    public void deleteReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
        reviewService.deleteReview(request);
        response.setStatus(HttpServletResponse.SC_OK);
    }


}
