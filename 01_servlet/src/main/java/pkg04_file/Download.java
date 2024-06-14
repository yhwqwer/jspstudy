package pkg04_file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		
		// 서버에 저장된 파일 이름
		String filename = request.getParameter("filename");
		
		// 사용자들에게 전달할 파일 이름 (다운로드용 파일 이름)
		String downloadFilename = filename.substring(filename.indexOf("_") + 1);
		
		// 브라우저에 따라서 인코딩 처리
		String userAgent = request.getHeader("User-Agent");
		
		
		// IE 
		if(userAgent.contains("Trident")) {
		  downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8").replace("+", " ");
		}
		
		// Edge
		else if(userAgent.contains("Edg")) {
		  downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");
		}
		
		// Other
		else {
		  downloadFilename = new String(downloadFilename.getBytes("UTF-8"), "ISO-8859-1");
		}
		
		// 다운로드용 응답 헤더 설정
		response.setHeader("Content-Type", "application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + downloadFilename);
		
		// 다운로드 구현(클라이언트 측으로 파일 출력하기)
		
		// 1. 서버에 저장된 파일 읽기
		String uploadPath = request.getServletContext().getRealPath("upload_dir");  
    File uploadDir = new File(uploadPath);
    File file = new File(uploadDir, filename);
    BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		
		// 2. 클라이언트로 읽은 내용 보내기
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		byte[] b = new byte[1024];
		int readByte = 0;
		while((readByte = in.read(b)) != -1) {
		  out.write(b, 0, readByte);
		}
    
    out.close();
    in.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
