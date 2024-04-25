package group2.bicycle_village.controller;

import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.service.AlarmService;
import group2.bicycle_village.service.AlarmServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.PrintWriter;

public class AlarmController implements RestController {
    private AlarmService alarmService = new AlarmServiceImpl();

    /**
     * 로그인한 유저가 팔로우한 유저가 게시물을 올릴 때 해당 유저에게 알림 추가
     * @param request
     * @param response
     * @throws Exception
     */
    public void insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("loginId");
//        String board = (String)session.getAttribute("board");

        String board = "insert";
        int result = 0;
        if(board != null) {
            try {
                result = alarmService.insertFollow(id, new AlarmDTO("board insert", 0, "alarm.jsp"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        PrintWriter out = response.getWriter();
        out.print(result);
    }

    /**
     * 로그인한 유저가 찜한 게시물이 수종되었을 때 해당 유저에게 알림 추가
     */

    /**
     * 팔로우한 유저가 게시물을 올렸는지 체크
     */

    /**
     * 찜한 게시물이 수정되었는지 체크
     */
}
