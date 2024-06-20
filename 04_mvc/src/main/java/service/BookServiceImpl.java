package service;

import java.util.Arrays;
import java.util.List;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.BookVO;

public class BookServiceImpl implements BookService {
  // 데이터베이스에 저장되어 있는 책으로 가정
  private List<BookVO> books = Arrays.asList(
      new BookVO(1,"태백산맥", "조정래", 1000),
      new BookVO(2,"홍길동전", "허균", 2000),
      new BookVO(3,"어린왕자", "생텍쥐베리", 3000),
      new BookVO(4,"소나기", "황순원", 4000)
  );
  
  @Override
  public ActionForward getBooks(HttpServletRequest request, HttpServletResponse response) {
    
    // JSP 로 전달할 데이터는 request 에 저장한다.
    request.setAttribute("books", books);
    
    // 어디로 : /book/list.jsp
    // 어떻게 : forward
    return new ActionForward("/book/list.jsp", false);
  }

  @Override
  public ActionForward getBookByNo(HttpServletRequest request, HttpServletResponse response) {
    
    // 요청 파라미터 bookNo
    int bookNo = Integer.parseInt(request.getParameter("bookNo"));
    
    // 반환할 Book 을 찾아서 request 에 저장하기
    books.stream().forEach(book ->{
      if(book.getBookNo() == bookNo) {
        request.setAttribute("book", book);
      }
    });
    
    // 어디로 : /book/detail.jsp
    // 어떻게 : forward    
    return new ActionForward("/book/detail.jsp", false);
  }

}
