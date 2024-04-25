package group2.bicycle_village.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

@WebServlet(urlPatterns = "/rest", loadOnStartup = 1)
public class RestDispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Map<String, RestController> map;
    Map<String, Class<?>> clzMap;
    @Override
    public void init(ServletConfig config) throws ServletException {

        ServletContext application = config.getServletContext();
        Object obj = application.getAttribute("restMap");
        map = (Map<String, RestController>)obj;

        clzMap = (Map<String, Class<?>>)config.getServletContext().getAttribute("restClzMap");

    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key"); //customer
        String methodName = request.getParameter("methodName"); //idCheck , insert , selectAll

        System.out.println("key = " + key+", methodName = " + methodName);
        try {
            Class<?> clz = clzMap.get(key);
            Method method = clz.getMethod(methodName, HttpServletRequest.class , HttpServletResponse.class);

            RestController controller = map.get(key);
            method.invoke(controller, request , response);

        }catch (Exception e) {
            e.printStackTrace();

        }
    }//service 메소드 끝



}
