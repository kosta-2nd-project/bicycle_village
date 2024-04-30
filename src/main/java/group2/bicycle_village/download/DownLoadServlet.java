package group2.bicycle_village.download;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downLoad")
public class DownLoadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 넘어오는 파일의 이름과 경로 받기
        String filePath = request.getParameter("filepath");

        // 파일 경로가 제공되지 않으면 에러 응답
        if (filePath == null || filePath.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File path is missing");
            return;
        }

        File file = new File(filePath);

        // 요청된 파일이 존재하지 않으면 에러 응답
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }

        // 요청된 파일의 mimeType을 설정한다(문서의 형태설정)
        String mimeType = getServletContext().getMimeType(file.toString());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);

        // 브라우저 별 파일 이름에 대한 한글 인코딩 설정
        String fileName = file.getName();
        if (request.getHeader("user-agent").indexOf("Trident") == -1) {
            fileName = new String(fileName.getBytes("UTF-8"), "8859_1");
        } else {
            fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
        }

        // 브라우저가 해석할 수 있는 파일을 해석하지 않고 다운로드
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\";");

        // 파일을 읽어서 클라이언트 브라우저에 출력
        try (FileInputStream fi = new FileInputStream(file);
             ServletOutputStream so = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fi.read(buffer)) != -1) {
                so.write(buffer, 0, length);
            }
        }
    }
}