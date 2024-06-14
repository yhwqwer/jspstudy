
package pkg08_databind;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyHttpServletRequest extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    request.setAttribute("a", "HttpServletRequest");
    
    // HttpServletRequest 의 정보는 리다이렉트 할 때 전달되지 않는다. 
    // response.sendRedirect("/servlet/dataconfirm");
    
    // HttpServletRequest 의 정보는 포워드 할 때 전달된다.
    request.getRequestDispatcher("/dataconfirm").forward(request, response);
    
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
