package group2.bicycle_village.controller;

import group2.bicycle_village.dao.ChattDAO;
import group2.bicycle_village.dao.ChattDAOImpl;
import group2.bicycle_village.service.MessageService;
import group2.bicycle_village.service.MessageServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;

public class MessageController implements RestController {
    ChattDAO chattDAO= new ChattDAOImpl();
    MessageService messageService= new MessageServiceImpl();



    public void insertMessage (HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse){

        String url = httpServletRequest.getParameter("url"); // AJAX를 통해 전송된 URL
        String userId= httpServletRequest.getParameter("userId");
        String content = httpServletRequest.getParameter("content");

        try {
           int result= chattDAO.findRoomIDByUrl(url);

            System.out.println(result+"roomId");
            System.out.println(userId+"userId");
            System.out.println(content+"content");
           int result2=messageService.insertMessage(result, userId,content);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
