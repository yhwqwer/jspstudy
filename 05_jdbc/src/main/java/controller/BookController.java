package controller;

import java.io.IOException;

import common.ActionForward;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BookService;
import service.BookServiceImpl;

public class BookController extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    request.setCharacterEncoding("UTF-8");
    
    String requestURI = request.getRequestURI();    /* http://localhost:9090/mvc/list.do */
    String contextPath = request.getContextPath();  /*                      /mvc         */
    int beginIndex = requestURI.indexOf(contextPath) + contextPath.length() + 1;
    String urlMapping = requestURI.substring(beginIndex);   /* list.do */
  
    BookService bookService = new BookServiceImpl();
    
    ActionForward actionForward = null;
    
    switch(urlMapping) {
    // 단순 이동 (서비스가 필요하지 않다. forward 로 이동한다.)
    case "write.do":
      actionForward = new ActionForward("/book/write.jsp", false);
      break;
    case "index.do":
      actionForward = new ActionForward("/index.jsp", false);
      break;
    // 비즈니스 로직 처리 (서비스가 필요하다.)
    case "list.do":
      actionForward = bookService.getBooks(request);
      break;
    case "detail.do":
      actionForward = bookService.getBookByNo(request);
      break;
    case "register.do":
      actionForward = bookService.registerBook(request);
      break;
    case "delete.do":
      actionForward = bookService.removeBook(request);
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
