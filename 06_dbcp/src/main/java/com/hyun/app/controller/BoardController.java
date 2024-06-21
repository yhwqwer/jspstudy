package com.hyun.app.controller;

import java.io.IOException;

import com.hyun.app.common.ActionForward;
import com.hyun.app.service.BoardService;
import com.hyun.app.service.BoardServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class BoardController extends HttpServlet {
  
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
    
    String requestURI = request.getRequestURI();    /* http://localhost:9090/mvc/list.do */
    String contextPath = request.getContextPath();  /*                      /mvc         */
    int beginIndex = requestURI.indexOf(contextPath) + contextPath.length() + 1;
    String urlMapping = requestURI.substring(beginIndex);   /* list.do */
  
    BoardService boardService = new BoardServiceImpl();
    
    ActionForward actionForward = null;
    
    switch(urlMapping) {
    // 단순 이동 (서비스가 필요하지 않다. forward 로 이동한다.)
    
    // 비즈니스 로직 처리 (서비스가 필요하다.)
    case "list.do":
      actionForward = boardService.getBoardList(request);
      break;
    }
    
    if(actionForward != null) {
      if(actionForward.isRedirect()) {
        response.sendRedirect(actionForward.getPath());
      } else {
        request.getRequestDispatcher(actionForward.getPath()).forward(request, response);
      }
    }
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
		
	}

}
