package pkg04_file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1
               , maxFileSize = 1024 * 1024 * 10 
               , maxRequestSize = 1024 * 1024 * 100)

public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 저장할 경로
		String uploadPath = request.getServletContext().getRealPath("upload_dir");  
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists())
		  uploadDir.mkdirs();
		
		// 첨부 파일의 정보
		Part part = request.getPart("profile");		
		
		// 첨부 파일의 원래 이름
		String originalFilename = null;
		if(part.getHeader("Content-Disposition").contains("filename")) {
		  originalFilename = part.getSubmittedFileName();
		}
		
		// 첨부 파일의 저장 이름
		String filesystemName = null;
		if(originalFilename != null) {
		  filesystemName = System.currentTimeMillis() + "_" + originalFilename;
		}
		
		// 첨부 파일 저장하기
		if(filesystemName != null) {
		  part.write(uploadPath + "/" + filesystemName);
		}
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<div><a href=\"/servlet/pkg04_file/NewFile.html\">첨부화면으로가기</a></div>");
		File[] files = uploadDir.listFiles();
		for(File file : files) {
		  String filename = file.getName();
		  out.println("<div>" + filename + "</div>");
		}
		out.flush();
		out.close();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
