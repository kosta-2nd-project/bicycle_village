package group2.bicycle_village.controller;

import com.google.gson.Gson;
import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.service.AlarmService;
import group2.bicycle_village.service.AlarmServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.util.List;

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
                result = alarmService.insertFollow(new AlarmDTO("follow insert success", 0, "home.jsp"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        PrintWriter out = response.getWriter();
        out.print(result);
    }

    /**
     * 로그인한 유저가 찜한 게시물이 수정되었을 때 해당 유저에게 알림 추가
     */

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("loginId");
        List<AlarmDTO> alarmList = alarmService.selectAllAlarm(id);

        Gson gson = new Gson();
        String json = gson.toJson(alarmList);

        PrintWriter out = response.getWriter();
        out.print(json);
        System.out.println("AlarmController.selectAll success!");
    }
}
