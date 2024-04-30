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

@WebServlet(name = "fileToImageServlet", value = "/file-servlet")
public class FileToImageServlet extends HttpServlet {
	
	@Override    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fname = req.getParameter("fname");
		File file = new File("/save/"+fname);
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int)file.length()];
		fis.read(data);
		fis.close();
		// 클라이언트에게 전달할 이미지의 MIME 타입 설정
		resp.setContentType("image/png"); // 이미지 타입에 따라 변경
		// 이미지 데이터를 출력 스트림을 통해 클라이언트에게 전송
		ServletOutputStream os = resp.getOutputStream();
		os.write(data);
		os.flush();
		os.close();
		
		}
	}

