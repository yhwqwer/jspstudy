package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DateModel;
import model.TimeModel;

@WebServlet("/datetime")
public class DateTimeController extends HttpServlet {
	
  private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 인코딩
	    request.setCharacterEncoding("UTF-8");
	    
	    // 요청 확인 (요청 파라미터 type 확인)
	    String type = request.getParameter("type");
	  
	    // 요청에 따른 비즈니스 로직 처리
	    String path = null;
	    switch(type) {
	    case "date":
	      DateModel dateModel = new DateModel();
	      path = dateModel.execute(request);  // Model 에 요청(request)을 전달한다.
	      break;
	    case "time":
	      TimeModel timeModel = new TimeModel();
	      timeModel.command(request, response);
	      break;
	    }
	    
	    // 응답 (결과를 보여 줄 JSP 로 이동하기 : 요청에 저장된 결과를 전달하기 위해서 forward 를 해야 한다.)
	    if(path != null) {
	      request.getRequestDispatcher(path).forward(request, response);
	    }
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
