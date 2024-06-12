
package pkg01_request;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class MyRequest extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // 1. 요청 정보 인코딩 (UTF-8)
    request.setCharacterEncoding("UTF-8");
    
    // 2. 요청 파라미터
    //   1) 모든 요청 파라미터는 String 타입이다.
    //   2) 파라미터가 없으면 null 값이 반환된다.
    //     (1) if 문으로 null 처리를 할 수 있다.
    //     (2) Optional<T> 클래스로 null 처리를 할 수 있다.
    
    /* if 문으로 null 처리 */
    /*
    String strAge = request.getParameter("age");
    int age = 0;  // 디폴트 값 (파라미터 age 가 전달되지 않으면 사용할 값)
    if(strAge != null)
      age = Integer.parseInt(strAge);    
    */
    
    /* Optional<T> 클래스로 null 처리 */
    Optional<String> opt = Optional.ofNullable(request.getParameter("age"));
    int age = Integer.parseInt(opt.orElse("0"));  // 디폴트 "0"
    
    String name = request.getParameter("name");
    
    // TODO Auto-generated method stub
    response.getWriter().append("Served at: ").append("age : " + age).append(", name : " + name);
    
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    // 요청 정보 인코딩 (UTF-8)
    request.setCharacterEncoding("UTF-8");
    
    // 요청 파라미터
    
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    String intro = request.getParameter("intro");
    System.out.println(id + " / " + pw + " / " + intro);
    
    String gender = request.getParameter("gender");
    System.out.println(gender);
    
    String game = request.getParameter("game");
    String travel = request.getParameter("travel");
    String lego = request.getParameter("lego");
    System.out.println(game + " / " + travel + " / " + lego);
    
    String[] hobbies = request.getParameterValues("hobbies");
    Arrays.stream(hobbies).forEach((hobby) -> {
      System.out.println(hobby);
    });
    
    String region = request.getParameter("region");
    String host = request.getParameter("host");
    System.out.println(region + " / " + host);
    
    String charencoding = request.getParameter("charencoding");
    String author = request.getParameter("author");
    System.out.println(charencoding + " / " + author);
    
  }

}
