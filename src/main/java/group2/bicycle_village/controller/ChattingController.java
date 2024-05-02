package group2.bicycle_village.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import group2.bicycle_village.common.dto.ChattDTO;
import group2.bicycle_village.exception.AuthenticationException;
import group2.bicycle_village.service.ChatServiceImpl;
import group2.bicycle_village.service.ChattService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChattingController implements RestController {

    ChattService chattService = new ChatServiceImpl();

    public void test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        System.out.println("ajax 채팅컨트롤");
    }


    public void createChat(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println("createChat 호출 ");


        String url = httpServletRequest.getParameter("url"); // AJAX를 통해 전송된 URL
        String sellerId= httpServletRequest.getParameter("sellerId");
        System.out.println("링크"+ url);
        System.out.println("sellerId"+sellerId);

        try {
            int result=chattService.insert(url,sellerId);
            System.out.println("체팅방생성결과 "+ result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

    }

    public void findUrlByUSerId(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String userId = httpServletRequest.getParameter("userId"); // AJAX를 통해 전송된 URL

        System.out.println("userId"+userId);


        try {
            List<ChattDTO>list =chattService.findUrlByUserId(userId);
            Gson gson = new Gson();
            String jsonArr = gson.toJson(list);
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.println(jsonArr);
            System.out.println(list+"리스트");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
    public void findRoomIdByUserId(HttpServletRequest req, HttpServletResponse res){
            String sellerId = req.getParameter("userId"); // AJAX를 통해 전송된 URL
            System.out.println(sellerId+"sellerwwwww");
        try {
            List<ChattDTO>list =    chattService.findUrlBySellerId(sellerId);
            Gson gson = new Gson();
            String jsonArr = gson.toJson(list);
            PrintWriter printWriter = res.getWriter();
            printWriter.println(jsonArr);
            System.out.println(list+"리스트");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
