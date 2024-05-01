package group2.bicycle_village.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import jakarta.servlet.http.HttpServletRequest;

public interface ReviewService {
    void createReview(HttpServletRequest request);
    JsonElement getReview(HttpServletRequest request);
    JsonElement getReviewListByUserSeq(HttpServletRequest request);
    void updateReview(HttpServletRequest request);
    void deleteReview(HttpServletRequest request);
}
