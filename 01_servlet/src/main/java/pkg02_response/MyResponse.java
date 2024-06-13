package pkg02_response;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyResponse extends HttpServlet {
  
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// response.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html");    // application/xml, application/json
		response.setContentType("text/html; charset=UTF-8");
		
		StringBuilder builder = new StringBuilder("<!DOCTYPE html>");
		builder.append("<html lang=\"ko\">");
		builder.append("<head>");
		builder.append("<meta charset=\"UTF-8\">");
		builder.append("<title>Home</title>");
		builder.append("</head>");
		builder.append("<body>");
		builder.append("<h1>아이디 : " + id + "</h1>");
		builder.append("<h1>비밀번호 : " + pw + "</h1>");
		builder.append("</body>");
		builder.append("</html>");
		
		PrintWriter out = response.getWriter();
		out.println(builder.toString());
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
