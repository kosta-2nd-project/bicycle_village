package group2.bicycle_village.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ReviewController  implements RestController, Controller{

    //자신이 받은 리뷰 조회
    //다른사람이 받은 리뷰 조회
    //자신이 작성한 리뷰 삭제
    //자신이 작성한 리뷰 조회


    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }
}
