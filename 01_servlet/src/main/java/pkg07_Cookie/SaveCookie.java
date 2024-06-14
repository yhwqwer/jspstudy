
package pkg07_Cookie;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SaveCookie extends HttpServlet {
 
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    request.setCharacterEncoding("UTF-8");
    
    String accept = request.getParameter("accept");
    
    if(accept != null) {  // 쿠키 허용하기 체크박스를 체크한 상태
      
      // 쿠키 만들기 (이름, 값(인코딩 해야 한다.))
      Cookie cookie1 = new Cookie("promotion_accept1", URLEncoder.encode("yes", "UTF-8"));
      Cookie cookie2 = new Cookie("promotion_accept2", URLEncoder.encode("yes", "UTF-8"));
      
      // 쿠키 유효 시간
      // 생략하면 세션 쿠키가 된다. (세션과 동일한 Life Cycle 을 가진다.)
      cookie1.setMaxAge(60 * 60 * 24 * 30);  // 30일 유지
      cookie2.setMaxAge(60 * 60 * 24 * 30);  // 30일 유지
      
      // 쿠키 활용 경로
      // 생략하면 ContextPath 가 사용된다. (ContextPath 가 포함된 모든 URL 에서 활용할 수 있다.) 
      cookie1.setPath("/servlet");
      cookie2.setPath("/servlet");
      
      // 쿠키 저장 (응답으로 처리)
      response.addCookie(cookie1);
      response.addCookie(cookie2);
      
      // 리다이렉트
      response.sendRedirect("/servlet/readcookie");
      
    }
  
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
