package group2.bicycle_village.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import group2.bicycle_village.common.dto.ChattDTO;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.exception.AuthenticationException;
import group2.bicycle_village.service.ChatServiceImpl;
import group2.bicycle_village.service.ChattService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChattingController implements RestController {

    ChattService chattService = new ChatServiceImpl();

    public void createChat(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println("채팅 컨트롤러 접근중");

        // axios로부터 전송된 JSON 데이터를 읽고 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // JSON 문자열을 Map으로 변환r
            Map<String, String> requestData = objectMapper.readValue(httpServletRequest.getReader(), Map.class);

            // 파싱된 데이터에서 값 추출
            String currentUrl = requestData.get("url");
            String sellerId= requestData.get("sellerId");
            String buyerId= requestData.get("buyerId");
            String content = requestData.get("content");
            System.out.println("전송된 URL: " + currentUrl);
            System.out.println("전송된 sellerId:"+ sellerId);
            System.out.println("전송된 buyerId:"+ buyerId);
            System.out.println("전송된 content:" + content);

               int result = chattService.insert(new ChattDTO( sellerId,buyerId,currentUrl,content));
            System.out.println(result+"채팅생성결과 ?");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateContent(HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse){
        System.out.println("업데이트 컨텐트 ");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // JSON 문자열을 Map으로 변환r
            Map<String, String> requestData = objectMapper.readValue(httpServletRequest.getReader(), Map.class);

            // 파싱된 데이터에서 값 추출
            String currentUrl = requestData.get("url");
            String content = requestData.get("content");
            System.out.println("전송된 URL: " + currentUrl);
            System.out.println("전송된 content:" + content);


            int result=chattService.updateChat(content,currentUrl);
            System.out.println("채팅업데이트 결과"+result);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    public void  findChatList (String userId){
        try {
           List<ChattDTO>findChatList= chattService.findChatList(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }
}
