
package pkg07_Cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReadCookie extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
       
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    // 쿠키 가져오기 (요청)
    Cookie[] cookies = request.getCookies();
    
    if(cookies == null) {
      return;
    }
      
    // 응답 스트림
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    for(Cookie cookie : cookies) {
      String cookieName = cookie.getName();
      String cookieValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
      out.println("<div>" + cookieName + ": " + cookieValue + "<button type=\"button\" data-cookie-name=\"" + cookieName + "\" class=\"delete-btn\">삭제</button></div>");
    }
    out.println("<script>");
    out.println("const deleteBtns = document.getElementsByClassName('delete-btn');");
    out.println("for(let i = 0; i < deleteBtns.length; i++){");
    out.println("  deleteBtns[i].onclick = evt=>{");
    out.println("    if(confirm('해당 쿠키를 삭제할까요?')){");
    out.println("      location.href=`/servlet/deletecookie?cookieName=${evt.target.dataset.cookieName}`");
    out.println("    } else {");
    out.println("      alert('취소되었습니다.')");
    out.println("    }");
    out.println("  }");
    out.println("}");
    out.println("</script>");
    out.close();
    
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
