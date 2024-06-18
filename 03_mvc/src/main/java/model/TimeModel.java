package model;

import java.io.IOException;
import java.time.LocalTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TimeModel {

  // 현재시간을 요청에 저장한 뒤 /views/time.jsp 로 이동하기
  
  public void command(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("now", LocalTime.now());
    request.getRequestDispatcher("/views/time.jsp").forward(request, response);
  }
  
  
}
