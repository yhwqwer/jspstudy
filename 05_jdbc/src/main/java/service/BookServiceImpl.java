package service;

import common.ActionForward;
import dao.BookDAO;
import dto.BookDTO;
import jakarta.servlet.http.HttpServletRequest;

public class BookServiceImpl implements BookService {

  // Field
  private BookDAO bookDAO = BookDAO.getInstance();
  
  @Override
  public ActionForward getBooks(HttpServletRequest request) {
    
    // 데이터베이스에서 가져온 List<BookDTO> 을 JSP 로 보내기 위해서 request 에 저장한 뒤 forward 한다.
    
    request.setAttribute("books", bookDAO.getBooks());
    
    // 삭제 후 목록 보기로 갈 때 삭제 결과를 JSP 로 보내기 위해서 request 에 저장한다.
    if(request.getParameter("deleteResult") != null)
      request.setAttribute("deleteResult", request.getParameter("deleteResult"));
    
    return new ActionForward("/book/list.jsp", false);
    
  }

  @Override
  public ActionForward getBookByNo(HttpServletRequest request) {
    
    // 전달된 bookNo 와 일치하는 책 정보를 데이터베이스로부터 가져와서 JSP 로 전달하기 위해 request 에 저장한 뒤 forward 한다.
    
    int bookNo = Integer.parseInt(request.getParameter("bookNo"));
    
    if(request.getParameter("modifyResult") != null) {
      int modifyResult = Integer.parseInt(request.getParameter("modifyResult"));
      request.setAttribute("modifyMessage", modifyResult == 1 ? "수정되었습니다." : "수정되지 않았습니다.");
    }
    
    request.setAttribute("book", bookDAO.getBookByNo(bookNo));
    
    return new ActionForward("/book/detail.jsp", false);
    
  }

  @Override
  public ActionForward registerBook(HttpServletRequest request) {
    
    // 요청 파라미터 (제목, 저자, 가격)
    String title = request.getParameter("title");
    String author = request.getParameter("author");
    int price = Integer.parseInt(request.getParameter("price"));

    // 데이터베이스로 전달할 형식인 BookDTO 타입의 객체 생성
    BookDTO book = BookDTO.builder()
        .title(title)
        .author(author)
        .price(price)
        .build();
    
    // 데이터베이스 실행
    int result = bookDAO.insertBook(book);
    
    // 성공하면 : /book/list.jsp 로 가기 위한 /list.do
    // 실패하면 : /index.jsp     로 가기 위한 /index.do
    String path = request.getContextPath() + (result == 1 ? "/list.do" : "/index.do");
    
    // 이동방식 : redirect (DML : INSERT, UPDATE, DELETE)
    return new ActionForward(path, true);
    
  }
  
  @Override
  public ActionForward removeBook(HttpServletRequest request) {


    // 삭제할 책 번호 (요청 파라미터)
    int bookNo = Integer.parseInt(request.getParameter("bookNo"));
    
    // 데이터베이스에서 삭제
    int result = bookDAO.deleteBook(bookNo);
    
    // 성공 / 실패 모두 /book/list.jsp 로 이동하기 위한 /list.do
    
    return new ActionForward(request.getContextPath() + "/list.do?deleteResult=" + result, true);
  }
  
  @Override
  public ActionForward editBook(HttpServletRequest request) {
    
    
    // 편집할 책의 정보를 JSP 로 전달하고 forward 한다.
    
    int bookNo = Integer.parseInt(request.getParameter("bookNo"));
    
    request.setAttribute("book", bookDAO.getBookByNo(bookNo));
    
    return new ActionForward("/book/edit.jsp", false);
    
    
  }
  
  @Override
  public ActionForward modifyBook(HttpServletRequest request) {
    
    int book_no = Integer.parseInt(request.getParameter("bookNo"));
    
    
    // 수정할 책의 정보를 저장한 BookDTO 만들기
    BookDTO book = BookDTO.builder()
        .book_no(Integer.parseInt(request.getParameter("bookNo")))
        .title(request.getParameter("title"))
        .author(request.getParameter("author"))
        .price(Integer.parseInt(request.getParameter("price")))
        .build();
    
    // 수정
    int result = bookDAO.updateBook(book);
    
    // 성공 / 실패 상관 없이 detail.jsp 로 redirect
    // 성공 / 실패 여부를 전달해서 /book/detail.jsp 가 메시지를 출력할 수 있도록 처리
    
        
    return new ActionForward(request.getContextPath() + "/detail.do?bookNo=" + book_no + "&modifyResult=" + result, true);
  }  
}
