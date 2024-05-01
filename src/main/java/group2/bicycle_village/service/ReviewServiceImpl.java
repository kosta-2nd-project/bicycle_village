package group2.bicycle_village.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import group2.bicycle_village.common.constant.CommonCode;
import group2.bicycle_village.common.dto.BoardEntity;
import group2.bicycle_village.common.dto.ReviewEntity;
import group2.bicycle_village.common.dto.UserEntity;
import group2.bicycle_village.dao.ReviewDAO;
import group2.bicycle_village.dao.ReviewDAOImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private static final ReviewDAO reviewDao = new ReviewDAOImpl();
    private static final Gson gson = new Gson();

    /*
     * 리뷰 생성 메서드
     * */
    @Override
    public void createReview(HttpServletRequest request) {
        reviewDao.createReview(
                new ReviewEntity
                        .Builder()
                        .reviewing(new UserEntity.Builder().userSeq(Long.parseLong(request.getParameter("reviewing"))).build())
                        .reviewer(new UserEntity.Builder().userSeq(Long.parseLong(request.getParameter("reviewer"))).build())
                        .board(new BoardEntity.Builder().boardSeq(Long.parseLong(request.getParameter("boardSeq"))).build())
                        .reviewContent(request.getParameter("content"))
                        .reviewScore(CommonCode.reviewScore.getScore(Integer.parseInt(request.getParameter("score"))))
                        .build()
        );
    }

    /*
     * 리뷰 상세 조회 메서드
     * */
    @Override
    public JsonElement getReview(HttpServletRequest request) {
        ReviewEntity result = reviewDao.getReviewByReviewSeq(Long.valueOf(request.getParameter("reviewSeq")));
        return gson.toJsonTree(result, ReviewEntity.class);
    }

    /*
     * 리뷰 리스트로 조회 메서드
     * @description: reviewer와 reviewing 기준 두 가지가 있다.
     * */
    @Override
    public JsonElement getReviewListByUserSeq(HttpServletRequest request) {
        List<ReviewEntity> result = request.getParameter("reviewing") != null ?
                reviewDao.getReviewListByReviewingWithUserSeq(Long.valueOf(request.getParameter("reviewing"))) :
                reviewDao.getReviewListByReviewerWithUserSeq(Long.valueOf(request.getParameter("reviewer")));
        return gson.toJsonTree(result);
    }

    /*
     * 리뷰 업데이트 메서드
     * */
    @Override
    public void updateReview(HttpServletRequest request) {
        reviewDao.putReview(
                new ReviewEntity
                        .Builder()
                        .reviewSeq(Long.parseLong(request.getParameter("reviewSeq")))
                        .reviewing(new UserEntity.Builder().userSeq(Long.parseLong(request.getParameter("reviewing"))).build())
                        .reviewer(new UserEntity.Builder().userSeq(Long.parseLong(request.getParameter("reviewer"))).build())
                        .board(new BoardEntity.Builder().boardSeq(Long.parseLong(request.getParameter("boardSeq"))).build())
                        .reviewContent(request.getParameter("content"))
                        .reviewScore(CommonCode.reviewScore.getScore(Integer.parseInt(request.getParameter("score"))))
                        .build()
        );
    }

    /*
     * 리뷰 삭제 메서드
     * */
    @Override
    public void deleteReview(HttpServletRequest request) {
        reviewDao.deleteReviewByReviewSeq(Long.valueOf(request.getParameter("reviewSeq")));
    }
}
