package group2.bicycle_village.controller;

import com.google.gson.Gson;
import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.service.AlarmService;
import group2.bicycle_village.service.AlarmServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlarmController implements RestController {
    private AlarmService alarmService = new AlarmServiceImpl();

    /**
     * 로그인한 유저가 팔로우한 유저가 게시물을 올릴 때 해당 유저에게 알림 추가
     * @param request
     * @param response
     * @throws Exception
     */
//    public void insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        HttpSession session = request.getSession();
//        String id = (String)session.getAttribute("loginId");
//        int result = 0;
//        if(id != null) {
//            try {
//                result = alarmService.insertFollow(new AlarmDTO("follow insert success", 0, "home.jsp"));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        PrintWriter out = response.getWriter();
//        out.print(result);
//    }

    /**
     * 로그인한 유저가 찜한 게시물이 수정되었을 때 해당 유저에게 알림 추가
     */

    /**
     * 로그인한 유저에게 알림 띄워주기
     * @param request
     * @param response
     * @throws Exception
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("loginId");
        List<AlarmDTO> alarmList = alarmService.selectAllAlarm(id);

        Gson gson = new Gson();
        String json = gson.toJson(alarmList);

        PrintWriter out = response.getWriter();
        out.print(json);
    }

    /**
     * 유저가 클릭한 게시물의 상태 변경(0 -> 1 : 확인함)
     * @param request
     * @param response
     * @throws Exception
     */
    public void updateAlarm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("loginId");
        long alarmSeq = Long.parseLong(request.getParameter("alarmSeq"));
        int result = alarmService.updateAlarm(id, alarmSeq);
        String url = null;
        if(result != 0) {
            url = alarmService.linked(alarmSeq);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", result);
        map.put("url", url);

        Gson gson = new Gson();
        String resultMap = gson.toJson(map);

        PrintWriter out = response.getWriter();
        out.print(resultMap);
    }
}
