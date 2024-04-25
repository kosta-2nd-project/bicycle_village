package group2.bicycle_village.filter;
import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionCheckFilter
 */
@WebFilter("/front")
public class SessionCheckFilter implements Filter {

    public SessionCheckFilter() {
    	System.out.println("SessionCheckFilter 생성됨...");
    }
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("SessionCheck....");
		//사전처리 -> 인증여부를 체크한다.
		String key = request.getParameter("key");
		if(key==null) {
			//인증된 사용자만 해라...
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session = req.getSession();
			if(session.getAttribute("loginUser") ==null) { //인증 안됨
				req.setAttribute("errorMsg", "로그인하고 이용해주세요.^^");
				req.getRequestDispatcher("error/error.jsp").forward(request, response);
				//Servlet을 들리지 않고 FailView로 바로 가버린다.
				return ;//함수를 빠져나가라
				//return을 적어주지 않으면 밑에서 doFilter가 실행되어 버린다!!
			}
		}
		
		chain.doFilter(request, response);
		//인증이 안되었을 때는 doFilter가 실행되면 안된다.
	}

}









