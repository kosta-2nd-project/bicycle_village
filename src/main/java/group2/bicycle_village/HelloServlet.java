/*
 * package group2.bicycle_village;
 * 
 * import java.io.*;
 * 
 * import group2.bicycle_village.dao.BoardDAOImpl; import
 * jakarta.servlet.http.*; import jakarta.servlet.annotation.*;
 * 
 * @WebServlet(name = "helloServlet", value = "/hello-servlet") public class
 * HelloServlet extends HttpServlet { private String message;
 * 
 * public void init() { message = "Hello World!"; }
 * 
 * public void doGet(HttpServletRequest request, HttpServletResponse response)
 * throws IOException { response.setContentType("text/html"); BoardDAOImpl
 * bookDao = new BoardDAOImpl(); bookDao.test(); // Hello PrintWriter out =
 * response.getWriter(); out.println("<html><body>"); out.println("<h1>" +
 * message + "</h1>"); out.println("</body></html>"); }
 * 
 * public void destroy() { } }
 */



package group2.bicycle_village;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.nio.file.Files;
import java.util.*;

@MultipartConfig
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 넘어오는 파일의 이름을 배열로 받기
        String[] fileNames = request.getParameterValues("fname");

        // 2. 저장폴더의 실제 경로를 얻어오기
        String saveDir = "/save/1";

        /*// 부가적인 옵션!!!
        // 요청된 파일의 mimeType을 설정한다(문서의 형태설정)
        String mimeType = getServletContext().getMimeType(fileNames[0]);
        if (mimeType == null) {
            response.setContentType("application/octet-stream");
        }
        System.out.println("mimeType = " + mimeType);*/

        // 반복문을 통해 각 파일 다운로드
        for (String fName : fileNames) {
            File file = new File(saveDir, fName);

            // 브라우져 별 파일이름에대한 한글인코딩설정
            String encodedFileName;
            if (request.getHeader("user-agent").indexOf("Trident") == -1) { // IE가 아닌경우
                System.out.println(1);
                encodedFileName = new String(file.getName().getBytes("UTF-8"), "8859_1");
            } else {
                System.out.println(2);
                encodedFileName = new String(file.getName().getBytes("euc-kr"), "8859_1");
            }

            // 브라우져가 해석할수 있는 파일을 해석하지 않고 다운로드!!!
            response.setHeader("Content-Disposition", "attachment;filename=\"" + encodedFileName + "\";");

            // 폴더에서 파일이름에 해당하는 파일을 읽어서 클라이언트 브라우져에서 다운로드(출력=쓰기)
            try (FileInputStream fi = new FileInputStream(file);
                 ServletOutputStream so = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fi.read(buffer)) != -1) {
                    so.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                // 파일 읽기 실패 처리
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Part> fileParts = req.getParts(); // 여러 파일 가져오기
        String uploadPath = "/save/" + req.getParameter("user_seq"); // 업로드할 디렉토리 경로 설정

        File directory = new File(uploadPath);
        if (!directory.exists()) {
            directory.mkdirs(); // 디렉토리 생성
        }


        for (Part filePart : fileParts) {
            String fileName = getFileName(filePart); // 업로드된 파일 이름 가져오기
            // 업로드된 파일 저장
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, new File(uploadPath + File.separator + fileName).toPath());
            } catch (IOException e) {
                // 오류 처리
            }
        }

        // 업로드 완료 후 처리
        resp.sendRedirect("https://naver.com");
    }

    // Part 객체에서 파일 이름 가져오기
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public void destroy() {
    }
}