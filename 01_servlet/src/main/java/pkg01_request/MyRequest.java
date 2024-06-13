
package pkg01_request;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * Servlet
 * 1. 클라이언트의 요청을 받아서 실행되는 Java 클래스이다.
 * 2. jakarta.servlet.http.HttpServlet 클래스를 상속 받는 클래스이다.
 * 3. Tomcat 에 의해서 실행된다.
 * 4. 동일한 프로젝트(컨텍스트) 내에서는 동일한 이름의 Servlet 이 존재할 수 없다.
 */


/*
 * URL
 * 1. 형식
 *  protocol://host:port/contextPath/urlMapping
 * 2. contextPath
 *  1) 프로젝트(컨텍스트)의 대표 경로를 의미한다.
 *  2) 프로젝트의 속성(properties)에서 변경할 수 있다.
 *    프로젝트 우클릭 - Properties - Web Project Setting
 * 3. urlMapping
 *  1) 프로젝트의 내부 경로를 의미한다.
 *  2) Servlet 의 호출 주소를 의미한다.
 *  3) 변경 방법
 *    (1) web.xml
 *    (2) @WebServlet
 */



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
