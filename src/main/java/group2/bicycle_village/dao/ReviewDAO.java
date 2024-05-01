package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.ReviewEntity;
import java.util.*;

public interface ReviewDAO {
    void createReview(ReviewEntity review);
    ReviewEntity getReviewByReviewSeq(long reviewSeq);
    List<ReviewEntity> getReviewListByReviewingWithUserSeq(long userSeq);
    List<ReviewEntity> getReviewListByReviewerWithUserSeq(long userSeq);
    void putReview(ReviewEntity reviewEntity);
    void deleteReviewByReviewSeq(long reviewSeq);
}
