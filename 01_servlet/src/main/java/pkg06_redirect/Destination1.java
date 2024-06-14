package pkg06_redirect;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Destination1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
    request.setCharacterEncoding("UTF-8");
    
    String luggage = request.getParameter("luggage");
    System.out.println("Destination1 : " + luggage);
		
    // redirect (요청 파라미터가 전달되지 않는다. 원한다면 다시 요청해야 한다.)
    response.sendRedirect("/servlet/destination2?luggage=" + luggage);
    
    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
