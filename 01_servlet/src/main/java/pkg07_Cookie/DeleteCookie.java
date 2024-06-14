
package pkg07_Cookie;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteCookie extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    request.setCharacterEncoding("UTF-8");
    
    String cookieName = request.getParameter("cookieName");
    
    // 쿠키 삭제 : 유효 기간이 0인 쿠키로 덮어쓰기
    Cookie cookie = new Cookie(cookieName, "");
    cookie.setMaxAge(0);
    response.addCookie(cookie);
    
    // 리다이렉트
    response.sendRedirect("/servlet/readcookie");
  
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
