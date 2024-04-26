package group2.bicycle_village.controller;


import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 모든 사용자 요청을 처리할 진입점 Controller의 역할
 */
@WebServlet(urlPatterns = "/front" , loadOnStartup = 1)
@MultipartConfig( //어노테이션을 통해  서블릿이 파일 업로드 기능을 할 수 있도록 웹 컨테이너에 지시
        maxFileSize = 1024 * 1024 * 5, //5M - 한 번에 업로드 할 수 있는 파일 크기 제한
        maxRequestSize = 1024 * 1024 * 50 //50M -전체 요청의 크기 제한. 기본값은 무제한 
)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, Controller> map;
	private Map<String, Class<?>> clzMap;
	
	public DispatcherServlet() {
		System.out.println("DispatcherServlet 생성자...");
	}
	
	@Override
	public void init() throws ServletException {
		ServletContext application = super.getServletContext();
		map = (Map<String, Controller>)application.getAttribute("map");
		clzMap = (Map<String, Class<?>>)application.getAttribute("clzMap");
	}
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key"); //
		String methodName = request.getParameter("methodName");
		
		System.out.println("key = " + key +" , methodName = " + methodName);
		try {
			Controller con = map.get(key);
			Class<?> clz = clzMap.get(key);
			Method method = 
					   clz.getMethod(methodName, HttpServletRequest.class , HttpServletResponse.class);
			
			ModelAndView mv = (ModelAndView)method.invoke(con, request, response);
			
			/////////////////////////////////////////////////////////
			if(mv.isRedirect()) {
				response.sendRedirect( mv.getViewName() );
			}else {
				request.getRequestDispatcher(mv.getViewName()).forward(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getCause().getMessage());
			request.getRequestDispatcher("error/error.jsp").forward(request, response);
		}
		
	}//service끝

}







