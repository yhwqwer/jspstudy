package model;

import java.time.LocalDate;

import jakarta.servlet.http.HttpServletRequest;

public class DateModel {

  // 현재날짜를 구한 뒤 요청에 저장하고 응답할 JSP 경로를 반환하는 메소드
  
  public String execute(HttpServletRequest request) {  // Controller 로부터 요청(request)를 전달 받는다.
    request.setAttribute("today", LocalDate.now());
    return "/views/date.jsp";
  }
  
}
