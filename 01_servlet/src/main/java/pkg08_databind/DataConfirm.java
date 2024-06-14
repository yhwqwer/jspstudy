
package pkg08_databind;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DataConfirm extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    // ServletContext
    ServletContext application = request.getServletContext();
    System.out.println("ServletContext : " + application.getAttribute("a"));
    
    // HttpSession
    HttpSession session = request.getSession();
    System.out.println("HttpSession : " + session.getAttribute("a"));
    
    // HttpServletRequest
    System.out.println("HttpServletRequest : " + request.getAttribute("a"));
  
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
