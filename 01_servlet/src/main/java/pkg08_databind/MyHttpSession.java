
package pkg08_databind;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MyHttpSession extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    HttpSession session = request.getSession();
    
    session.setAttribute("a", "HttpSession");
    
    // 세션 유지 시간
    // 생략하면 30분
    session.setMaxInactiveInterval(60);  // 60초, -1과 같은 음수 전달 시 무한으로 활용
    
    response.sendRedirect("/servlet/dataconfirm");
    
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
