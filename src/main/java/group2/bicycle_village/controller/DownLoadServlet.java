package group2.bicycle_village.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/downLoad")
public class DownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 넘어오는 파일의 이름을 받기
		String fName = request.getParameter("fname");
		
		//2. 저장폴더의 실제 경로를 얻어오기
		String saveDir=request.getServletContext().getRealPath("/save");
		File file = new File(saveDir, fName);
		
		//부가적인 옵션!!!
		//요청된 파일의 mimeType을 설정한다(문서의 형태설정)
		String mimeType = getServletContext().getMimeType(file.toString()); // mimeType=문서의 형태
		
		if(mimeType==null){
			response.setContentType("application/octet-stream");
		}
		
		System.out.println("mimeType = " + mimeType);
		
		
		//브라우져 별 파일이름에대한 한글인코딩설정
		if (request.getHeader("user-agent").indexOf("Trident") == -1) {// IE가 아닌경우
			System.out.println(1);
			fName = new String(file.getName().getBytes("UTF-8"), "8859_1");
		} else {
			System.out.println(2);
			fName = new String(file.getName().getBytes("euc-kr"), "8859_1");
		}
		
		//브라우져가 해석할수 있는 파일을 해석하지 않고 다운로드!!!
		response.setHeader("Content-Disposition", "attachment;filename=\""+ fName + "\";");
				
		//3. 폴더에서 파일이름에 해당하는 파일을 읽어서 
		//클라이언트 브라우져에서 다운로드(출력=쓰기)

		FileInputStream  fi = new FileInputStream(file); // 읽고
		
		ServletOutputStream so = response.getOutputStream(); // 쓰기
		
		byte b [] = new byte [1024];
	   
		int i=0;
		while((i = fi.read(b) ) != -1){
			so.write(b);
		}
		
		so.flush();
		fi.close();
		so.close();
		
	}

}






